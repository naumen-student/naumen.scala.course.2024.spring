## ZIO
## Асинхронность и многопоточность
## Управление ресурсами


<br>
<br>
Глеб Тумин

---

![](images/multthread.svg)

---

### Параллельное исполнение

* Параллельное исполнение подразумевает наличие <span class="naumen">более, чем одного</span> вычислительного устройства (например, процессора), которые <span class="naumen">в точно одно и то же время</span> выполняют несколько задач


---
### Конкуретное исполнение

* Конкурентное исполнение подразумевает, что несколько действий находятся в процессе выполнения одновременно (но не обязательно в точности в одно и то же время).


---
### Асинхронность

* Асинхронность означает, что результат операции будет доступен не сразу. Такая операция может быть выполнена и на стороне – удалённым веб-узлом, сервером или другим устройством за пределами текущего вычислительного устройства.

---
### Базовые возможности concurrency в Scala

---
### Future

* Неблокирующие, используют callback
* Цепочку Future можно комбинировать через map/flatMap

```scala
val getTopAuthors: Future[Seq[Author]] = for {
    books: Seq[Book]      <- bookService.getTop10()
    authorIds: Seq[String] = books.map(_.authorId)
    authors: Seq[Author]  <- authorService.getByIds(authorIds)
} yield {
    authors
}
```

---
#### ExecutionContext

* Future запускается в рамках контекста исполнения
* Вычисления выполняются в новом потоке, в потоке из пула или в текущем
* Существует фиксированный глобальный контекст - используется по умолчанию
* Можно создавать свои контексты исполнения со своими пулами потоков

---
### Параллельная обработка коллекций

```scala
val lst = someList()

lst.map(hardElementOperation(_))
lst.par.map(hardElementOperation(_))
```

---
### Overhead при работе с потоками

1. Выделение потока дорогостоящая операция
1. Содержание потока ресурсоёмко
1. Переключеие между потоками занимает время
1. Отсутствие возможности композиции

---
### Green threads - альтернатива классическим потокам

* Управляет ВМ, а не ОС
* Управление происходит в пространстве пользователя, а не ядра
* Превосходят встроенные потоки Linux-системы по времени активации потоков и синхронизации

---
### ZIO Fibers

* Можно рассматривать как <span class="naumen">виртуальный</span> поток
* Аналог java.lang.Thread, но <span class="naumen">производительнее</span>
* <span class="naumen">Один поток JVM</span> будет исполнять <span class="naumen">несколько файберов</span>

---

![](images/fibers.svg)

---
### ZIO Fibers преимущества

* Неограниченное количество
* Легковесность
* Возможность компоновки
* Безопасное прерывание потока
* Иерархия потоков

---

### ZIO Fibers. Типы данных

1. `Fiber` - сам виртуальный поток
1. `Fiber.Status` - текущее состояние потока
1. `FiberId` - уникальный идентификатор потока

---


### ZIO Fibers. Пример

```scala
private def boilWater(): ZIO[Any, Nothing, Unit] = {...} // delay(1000.milli)
private def fryEggs(): ZIO[Any, Nothing, Unit] = {...} // delay(2000.milli)

def makeBreakfast: ZIO[Any, Nothing, Unit] = for {
    waterFiber <- boilWater()
    eggsFiber <- fryEggs()
} yield ()
```

---

### ZIO Fibers. Пример

```scala
private def boilWater(): ZIO[Any, Nothing, Unit] = {...}
private def fryEggs(): ZIO[Any, Nothing, Unit] = {...}

def makeBreakfast: ZIO[Any, Nothing, Unit] = for {
    waterFiber <- boilWater().fork
    eggsFiber <- fryEggs().fork
} yield ()
```

---

### ZIO Fibers. Пример

```scala
private def boilWater(): ZIO[Any, Nothing, Unit] = {...}
private def fryEggs(): ZIO[Any, Nothing, Unit] = {...}

def makeBreakfast: ZIO[Any, Nothing, Unit] = for {
    waterFiber <- boilWater().fork
    eggsFiber <- fryEggs().fork
    _ <- waterFiber.zip(eggsFiber).await
} yield ()

```

---
### ZIO Fibers. Основные функции

* fork
* forkDaemon
* await
* join
* interrupt

---
### ZIO to Fibers

* race
* zipPar
* foreachPar

---
### ZIO Fibers. Прерывание потока
#### Причины

* Нет необходимости ожидать все потоки (например, нужен только первый результат)
* Прерывать по таймауту
* Отмена выполнения пользователем (интерактивные приложения)

---
### ZIO Fibers. Прерывание потока
#### Способы

* Полуасинхронное прерывание (опрос для прерывания) - поток сам периодечески проверяет, нет ли ему команды на остановку
* Асинхронное прерывание — поток позволяет прервать другой поток

---
### ZIO Fibers. Прерывание потока
#### Fiber#interrupt

```scala
def task = for {
    fn <- ZIO.fiberId.map(_.threadName)
    _ <- ZIO.debug(s"$fn starts a long running task")
    _ <- ZIO.sleep(1.minute)
    _ <- ZIO.debug("done!")
} yield ()

def run = for {
    f <- task
        .onInterrupt(ZIO.debug(s"Task interrupted while running"))
        .fork
    _ <- f.interrupt
} yield ()
```

---
### ZIO Fibers. Прерывание потока
#### Прерывание блокирующих операций

```scala
for {
  fiber <- ZIO.attemptBlocking {
    while (true) {
      Thread.sleep(1000)
      println("Doing some blocking operation")
    }
  }.ensuring(
    Console.printLine("End of a blocking operation").orDie
  ).fork

  _ <- fiber.interrupt.schedule(
    Schedule.delayed(
      Schedule.duration(1.seconds)
    )
  )
} yield ()
```

---
### ZIO Ресурсы
#### Стандартный подход

```scala
def lines(file: String) = ZIO.attempt {
  def countLines(reader: BufferedReader): Long = reader.lines().count()
  val bufferedReader = new BufferedReader(
    new InputStreamReader(new FileInputStream("file.txt")),
    2048
  )
  val count = countLines(bufferedReader)
  bufferedReader.close()
  count
}
```

---
### ZIO Ресурсы
#### Стандартный подход

```scala
def lines(file: String) = ZIO.attempt {
  def countLines(br: BufferedReader): Long = br.lines().count()
  val bufferedReader = new BufferedReader(
    new InputStreamReader(new FileInputStream("file.txt")),
    2048
  )
  try countLines(bufferedReader)
  finally bufferedReader.close()
}
```

---
### ZIO Ресурсы
#### Acquire Release

```scala
def use(resource: Resource): Task[Any]     = ZIO.attempt(???)
def release(resource: Resource): UIO[Unit] = ZIO.succeed(???)
def acquire: Task[Resource]                = ZIO.attempt(???)

val result: Task[Any] = ZIO.acquireReleaseWith(acquire)(release)(use)
```


---
### ZIO Ресурсы
#### Acquire Release

```scala
def lines(file: String): Task[Long] = {
  def countLines(reader: BufferedReader) = {
    ZIO.attempt(reader.lines().count())
  }
  def releaseReader(reader: BufferedReader) = {
    ZIO.succeed(reader.close())
  }
  def acquireReader(file: String)= {
    ZIO.attempt(new BufferedReader(new FileReader(file), 2048))
  }

  ZIO.acquireReleaseWith(acquireReader(file))
  (releaseReader)(countLines)
}
```

---
### ZIO Ресурсы
#### Acquire Release

```scala
def transfer(source: String, destination: String) = {
  ZIO.acquireReleaseWith(inputStream(source))(close) { in =>
    ZIO.acquireReleaseWith(
        outputStream(destination)
        )(close) { out =>
      copy(in, out)
    }
  }
}
```

---
### ZIO Ресурсы
#### Acquire Release

```scala
def transfer(source: String, destination: String) =
  ZIO.acquireReleaseWith {
    inputStream(source).zipPar(outputStream(destination))
  } { case (in, out) =>
    ZIO.succeed(in.close()).zipPar(ZIO.succeed(out.close()))
  } { case (in, out) =>
    copy(in, out)
  }
```

---
### ZIO Ресурсы
#### Scope

```scala
val scoped = ZIO.acquireRelease(acquire)(release)
```

---

### ZIO Ресурсы
#### Scope

```scala
def transfer(from: String, to: String) = {
  val resource = for {
    from <- ZIO.acquireRelease(is(from))(close)
    to   <- ZIO.acquireRelease(os(to))(close)
  } yield (from, to)

  ZIO.scoped {
    resource.flatMap { case (in, out) =>
      copy(in, out)
    }
  }
}
```

---

### Спасибо за внимание!
