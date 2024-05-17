package ru.naumen.scala_course.JobAggregator.user.dao.repositories

import doobie.quill.DoobieContext
import io.getquill.{CompositeNamingStrategy2, Escape, Literal}
import ru.naumen.scala_course.JobAggregator.persistent.DBTransactor
import ru.naumen.scala_course.JobAggregator.user.dao.entities.User
import zio.{Has, ULayer, ZLayer}

object UserRepository {

    val dc: DoobieContext.Postgres[CompositeNamingStrategy2[Escape.type, Literal.type]] =
        DBTransactor.doobieContext

    import dc._

    type UserRepository = Has[Service]

    trait Service {

        def find(id: String): Result[Option[User]]

        def add(user: User): Result[Unit]

    }

    class ServiceImpl extends Service {

        private val userSchema = quote(querySchema[User](""""User""""))

        override def find(id: String): Result[Option[User]] =
            dc.run(quote(userSchema.filter(user => user.id == lift(id)).take(1)))
                .map(_.headOption)

        override def add(user: User): Result[Unit] =
            dc.run(quote(userSchema.insert(lift(user))))
    }

    val live: ULayer[UserRepository] = ZLayer.succeed(new ServiceImpl)

}
