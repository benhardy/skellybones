package skellybones.service

import skellybones.bo.Person

/**
  */
trait PersonService {
  def findById(id: Int): Option[Person]
}
