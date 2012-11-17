package skellybones.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._
import org.springframework.web.servlet.ModelAndView
import org.springframework.beans.factory.annotation.Autowired
import skellybones.service.PersonService
import skellybones.web.ResourceNotFoundException
import skellybones.bo.Person

/**
 * An example of a web controller
 */
@Controller
class PersonController @Autowired()(// constructor is autowired
                                    personService: PersonService
                                     ) {

  @RequestMapping(Array("/hello"))
  @ResponseBody
  def hello = {
    "Welcome to ZenMaster"
  }

  /**
   * shows people wrapped inside HTML view demo.jsp
   */
  @RequestMapping(value = Array("/demo/{id}"), method = Array(RequestMethod.GET), produces = Array("text/html"))
  def demo(@PathVariable("id") id: Int) = {
    // associate person instance with "person" attribute inside demo view
    new ModelAndView("demo", "person", person(id))
  }

  /**
   * shows people as JSON via automatic jackson object rendering
   */
  @RequestMapping(value = Array("/person/{id}"), method = Array(RequestMethod.GET), produces = Array("application/json"))
  @ResponseBody
  def person(@PathVariable("id") id: Int): Person = {
    personService findById id getOrElse {
      throw new ResourceNotFoundException
    }
  }

}
