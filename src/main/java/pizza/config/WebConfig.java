package pizza.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"pizza.web"})
public class WebConfig extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Bean
	public FlowController flowController(FlowExecutor flowExecutor) {
		FlowController flowController = new FlowController();
		flowController.setFlowExecutor(flowExecutor);
		return flowController;
	}
	
//	@Bean
//	public HandlerMapping viewMappings() {
//		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
//		Properties properties = new Properties();
//		// 映射特定路径到 flowController
//		properties.setProperty("/shopping.do", "flowController");
//		handlerMapping.setMappings(properties);
//		handlerMapping.setDefaultHandler(new UrlFilenameViewController());
//		return handlerMapping;
//	}
	
	/**
	 *  将对静态资源的请求转发到 Servlet 容器中默认的 Servlet 上
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	

}
