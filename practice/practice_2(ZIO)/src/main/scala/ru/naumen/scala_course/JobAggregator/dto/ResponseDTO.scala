package ru.naumen.scala_course.JobAggregator.dto

import io.circe.{Codec, Decoder, Encoder}
import io.circe.generic.semiauto.deriveCodec
import ru.naumen.scala_course.JobAggregator.constants.{pageNumbers, sorting}
import sttp.tapir
import sttp.tapir.Codec.PlainCodec
import sttp.tapir.{Schema, Validator}

case class ResponseDTO[T](success: Boolean,
                          code: String,
                          message: Option[String],
                          data: Option[T])

object ResponseDTO {
  implicit def codec[T: Encoder: Decoder: Schema]: Codec[ResponseDTO[T]] = deriveCodec[ResponseDTO[T]]

  def ok[T]: ResponseDTO[T] = ok(code = "OK")
  def ok[T](data: T): ResponseDTO[T] = ok(code = "OK", data = Some(data))
  def ok[T](
             data: Option[T] = None,
             code: String,
             message: Option[String] = None): ResponseDTO[T] = ResponseDTO(success = true, code, message, data)

  def createDTOForPagedList[T](dtos: Seq[T], pagination: PaginationResponseDTO, sorting: Option[SortingDTO] = None): PagedListDTO[T] = PagedListDTO(dtos, Some(pagination), sorting)

  def createDTOForList[T](dtos: Seq[T]): ListDTO[T] = ListDTO(dtos)

  def createError[T](code: String,
                     message: Option[String] = None): ResponseDTO[T] = ResponseDTO(success = false, code, message, None)

}

case class DTOWithID[T](id: String, dto: T)

object DTOWithID{
  implicit def codec[T](implicit c: Codec[T]): Codec.AsObject[DTOWithID[T]] = deriveCodec[DTOWithID[T]]
}

case class TitleWithId(id: String, title: String)

object TitleWithId{
  implicit val codec: Codec[TitleWithId] = deriveCodec
}

case class PaginationResponseDTO(pageNumber: Long, pageSize: Int, numberOfPages: Long, numberOfRows: Long)
case class PaginationRequestDTO(pageNumber: Long, pageSize: Int)
case class SortingDTO(sortBy: String, sortDirection: String)
case class ListDTO[T](list: Seq[T])
case class PagedListDTO[T](
                            list: Seq[T],
                            pagination: Option[PaginationResponseDTO],
                            sorting: Option[SortingDTO]
                          )

object PaginationResponseDTO {
  implicit val codec: Codec[PaginationResponseDTO] = deriveCodec

  def countNumberOfPages(paginationRequestDTO: PaginationRequestDTO, totalRows: Int): Int = {
    val reminder = totalRows % paginationRequestDTO.pageSize
    (totalRows) / paginationRequestDTO.pageSize + {if(reminder > 0) 1 else 0}
  }
}

object PaginationRequestDTO {
  implicit val codec: Codec[PaginationRequestDTO] = deriveCodec

  def from(pageNumberOpt: Option[Long], pageSizeOpt: Option[Int]) = PaginationRequestDTO(
    pageNumberOpt.getOrElse(pageNumbers.defaultPageNumber),
    if (pageSizeOpt.nonEmpty && pageNumbers.pageSizeList.contains(pageSizeOpt.get))
      pageSizeOpt.get
    else pageNumbers.defaultPageSize
  )
}

object SortingDTO {
  implicit val codec: Codec[SortingDTO] = deriveCodec

  def from(sortByOpt: Option[String],
           sortDirectionOpt: Option[String],
           defaultSortColumn: String) = SortingDTO(
    sortByOpt.getOrElse(defaultSortColumn),
    sortDirectionOpt.getOrElse(sorting.ascSortDirection)
  )
}

object ListDTO {
  implicit def codec[T: Encoder: Decoder: Schema]: Codec[ListDTO[T]] = deriveCodec[ListDTO[T]]
}

object PagedListDTO {
  implicit def codec[T: Encoder: Decoder: Schema]: Codec[PagedListDTO[T]] = deriveCodec[PagedListDTO[T]]
}

case class PageSize(pageSize: Int) extends AnyVal

object PageSize{
  implicit val codec: PlainCodec[PageSize] = {
    tapir.Codec.int.map(value => PageSize(value))(s => s.pageSize)
  }
  val default = PageSize(10)
}

case class PageNumber(pageNumber: Int) extends AnyVal

object PageNumber{
  implicit val codec: PlainCodec[PageNumber] = {
    tapir.Codec.int.map(value => PageNumber(value))(s => s.pageNumber)
  }
  val default = PageNumber(1)
}