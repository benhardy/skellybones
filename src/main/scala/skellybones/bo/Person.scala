package skellybones.bo

import org.joda.time.DateMidnight
import reflect.BeanProperty

/**
 * an example business object.
 */
case class Person(@BeanProperty id: Int,
                  @BeanProperty name: String,
                  @BeanProperty phone: String,
                  @BeanProperty dob: DateMidnight) {

}
