package ru.naumen.scala_course.JobAggregator.user.services

import ru.naumen.scala_course.JobAggregator.errorsModel.JobAggregatorError
import ru.naumen.scala_course.JobAggregator.persistent.DBTransactor
import ru.naumen.scala_course.JobAggregator.user.dao.repositories.UserRepository
import ru.naumen.scala_course.JobAggregator.user.dao.repositories.UserRepository.UserRepository
import ru.naumen.scala_course.JobAggregator.user.services.dtos.UserDTO
import zio.{Has, ULayer, ZIO, ZLayer}
import doobie.implicits._
import ru.naumen.scala_course.JobAggregator.errorsModel.JobAggregatorError.InternalError
import zio.interop.catz._

object UserService {

    type UserService = Has[Service]

    trait Service {

        def findBy(id: String): ZIO[DBTransactor, JobAggregatorError, UserDTO]

        def add(user: UserDTO): ZIO[DBTransactor, JobAggregatorError, Unit]

    }

    class ServiceImpl(userRepository: UserRepository.Service) extends Service {

        override def findBy(id: String): ZIO[DBTransactor, JobAggregatorError, UserDTO] =
            for {
                transactor <- DBTransactor.dbTransactor
                user <- userRepository.find(id).transact(transactor)
                    .mapError(err => InternalError(err.getMessage))
            } yield user.map(UserDTO.from).get
    }

    val live: ZLayer[UserRepository, Nothing, UserService] =
        ZIO.service[UserRepository.Service].map(new ServiceImpl(_)).toLayer

}
