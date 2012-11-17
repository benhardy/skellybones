package skellybones.web.controller

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import skellybones.service.PersonService
import skellybones.web.ResourceNotFoundException
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import org.mockito.Matchers._
import skellybones.bo.Person
import org.joda.time.DateMidnight

/**
  */
@RunWith(classOf[JUnitRunner])
class PersonControllerTest extends FunSuite with ShouldMatchers with MockitoSugar {

  test("should throw 404 exception if person resource not found") {
    val ps = mock[PersonService]
    when(ps.findById(anyInt())).thenReturn(None)
    val c = new PersonController(ps)
    intercept[ResourceNotFoundException] {
      val res = c.person(5)
    }
  }
  test("should return any people found") {
    val ps = mock[PersonService]
    when(ps.findById(anyInt())).thenReturn(None)
    val fred = Person(5, "Fred", "123-456-7890", new DateMidnight(2010, 1, 12))
    when(ps.findById(5)).thenReturn(Some(fred))

    val c = new PersonController(ps)
    val res = c.person(5)

    res should be === fred
  }

}
