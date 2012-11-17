package skellybones.service

import skellybones.bo.Person
import org.springframework.stereotype.Component
import org.joda.time.DateMidnight

/**
 * a fake example service which just does people lookups. in real life you'd get
 * this to talk to a data store of some kind
 */
@Component
class PersonServiceImpl extends PersonService {

  private val knownPeople = Map(
    3 -> Person(3, "Fred", "310-123-1234", new DateMidnight(1972, 1, 5)),
    4 -> Person(4, "Fred", "310-567-5688", new DateMidnight(1960, 12, 22))
  )

  override def findById(id: Int) = {
    knownPeople get id
  }

}
