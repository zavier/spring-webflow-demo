package pizza.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

@Configuration
public class WebflowConfig extends AbstractFlowConfiguration {
	
	@Autowired
	private WebConfig webConfig;
	
	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		return getFlowDefinitionRegistryBuilder().setBasePath("/WEB-INF/flows")
				.addFlowLocationPattern("*-flow.xml").build();
//		System.out.println("==============================");
//		System.out.println("==============================");
//		System.out.println("==============================");
//		System.out.println("==============================");
//		System.out.println("==============================");
//		return getFlowDefinitionRegistryBuilder(flowBuilderServices())
//			.addFlowLocation("/WEB-INF/flows/pizza/total-flow.xml", "pizza")
//			.addFlowLocation("/WEB-INF/flows/pizza/customer/customer-flow.xml", "pizza/customer")
//			.addFlowLocation("/WEB-INF/flows/pizza/order/order-flow.xml", "pizza/order")
//			.addFlowLocation("/WEB-INF/flows/pizza/payment/payment-flow.xml", "pizza/payment")
//			.build();
	}
	
	@Bean
	public FlowExecutor flowExecutor() {
		return getFlowExecutorBuilder(flowRegistry()).build();
	}
	
	@Bean
	public MvcViewFactoryCreator mvcViewFactoryCreator() {
		MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
		factoryCreator.setViewResolvers(Arrays.<ViewResolver>asList(this.webConfig.viewResolver()));
		factoryCreator.setUseSpringBeanBinding(true);
		return factoryCreator;
	}
	
	@Bean
	public FlowHandlerMapping flowHandlerMapping() {
		FlowHandlerMapping flowHandlerMapping = new FlowHandlerMapping();
		flowHandlerMapping.setFlowRegistry(flowRegistry());
		return flowHandlerMapping;
	}
	
	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {
		FlowHandlerAdapter flowHandlerAdapter = new FlowHandlerAdapter();
		flowHandlerAdapter.setFlowExecutor(flowExecutor());
		return flowHandlerAdapter;
	}
	
	@Bean
	public FlowBuilderServices flowBuilderServices() {
		return getFlowBuilderServicesBuilder()
				.setViewFactoryCreator(mvcViewFactoryCreator()).build();
	}
}
