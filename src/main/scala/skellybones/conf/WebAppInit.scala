package skellybones.conf

import org.springframework.web.WebApplicationInitializer
import javax.servlet.ServletContext
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.servlet.DispatcherServlet

/**
 * Our instance of WebApplicationInitializer. This replaces web.xml for the initial
 * configuration of the servlet.
 */
class WebAppInit extends WebApplicationInitializer {

  override def onStartup(servletContext: ServletContext) {

    // Create the 'root' Spring application context
    val root = new AnnotationConfigWebApplicationContext()
    root.scan("skellybones")


    // Manages the lifecycle of the root application context
    servletContext.addListener(new ContextLoaderListener(root))

    val servlet = new DispatcherServlet(root)
    val appServlet = servletContext.addServlet("appServlet", servlet)
    appServlet.setLoadOnStartup(1)
    val mappingConflicts = appServlet.addMapping("/")
    if (!mappingConflicts.isEmpty) {
      throw new IllegalStateException("'appServlet' could not be mapped to '/'")
    }
  }
}
