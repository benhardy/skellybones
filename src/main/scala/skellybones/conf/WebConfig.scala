package skellybones.conf

import org.springframework.context.annotation.{PropertySource, Configuration, ComponentScan, Bean}
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.beans.factory.annotation.{Value, Autowired}
import org.springframework.core.env.Environment
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import reflect.BeanProperty

@Configuration("springConf")
@EnableWebMvc
@ComponentScan(Array("skellybones"))
@PropertySource(Array("classpath:/app.properties", "${runtime.properties.file}"))
class WebConfig {
  @Autowired
  var environment: Environment = null

  /**
   * this needs to be initialized before anything else, so we make it load from
   * companion class.
   */
  @Bean
  val props = WebConfig.props

  @Value("#{environment['root.url'] ?: 'http://localhost:8080'}")
  @BeanProperty
  var rootUrl: String = ""

  @Value("#{environment['maven.project.artifactId']}")
  @BeanProperty
  var projectArtifactId: String = ""

  @Value("#{environment['maven.project.groupId']}")
  @BeanProperty
  var projectGroupId: String = ""

  @Value("#{environment['maven.project.version']}")
  @BeanProperty
  var projectVersion: String = ""

  @Bean
  def viewResolver = {
    val resolver = new InternalResourceViewResolver()
    resolver setPrefix "/WEB-INF/jsp/"
    resolver setSuffix ".jsp"
    resolver setExposedContextBeanNames Array("springConf")
    resolver
  }
}

object WebConfig {
  /**
   * this needs to be static to get initialized before anything else.
   */
  @Bean
  def props = {
    new PropertySourcesPlaceholderConfigurer()
  }
}
