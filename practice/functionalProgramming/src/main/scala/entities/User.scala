package entities

case class User(id: Int, login: String, password: String)

case class UserId(raw: Int)