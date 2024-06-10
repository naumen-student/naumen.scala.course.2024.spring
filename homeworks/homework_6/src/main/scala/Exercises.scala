import utils.PictureGenerationService.PictureGenerationService
import utils.Utils._
import zio.{IO, Random, URIO, ZIO}
import utils.{ColorService, PictureGenerationService}

import java.awt.Color

@@ -12,7 +13,7 @@
object Exercises {
  def task1(r: Int, g: Int, b: Int): URIO[ColorService, Option[Color]] =
    ZIO.serviceWithZIO[ColorService](_.getColor(r, g, b)).option


  @@ - 22, 7 + 23, 9 @@

  object Exercises {
    def task2(size: (Int, Int)): ZIO[PictureGenerationService, GenerationError, String] =
      ZIO.serviceWithZIO[PictureGenerationService](_.generatePicture(size)).map(p => {
        p.lines.map(l => l.map(el => -el.getRGB()).mkString(" ")).mkString("\n")})


    @@ - 37, 15 + 40, 17 @@

    object Exercises {
      for { colorServ <- ZIO.service[ColorService]
        pictureServ <- ZIO.service[PictureGenerationService]
        color <- colorServ.generateRandomColor().mapError(_ => new GenerationError("Не удалось создать цвет"))
        picture <- pictureServ.generatePicture(size).mapError(_ => new GenerationError("Ошибка генерации изображения"))
        filledPicture <- pictureServ.fillPicture(picture, color).mapError(_ => new GenerationError("Возникли проблемы при заливке изображения"))}
      yield filledPicture


      def task4(size: (Int, Int)): IO[GenerationError, Picture] =
        task3(size)
          .provideSomeLayer(PictureGenerationService.live)
          .provideLayer(ColorService.live)
    }
  }
}