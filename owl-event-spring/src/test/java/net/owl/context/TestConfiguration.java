package net.owl.context;

import net.owl.action.ActionRepository;
import net.owl.context.ActionLoader;
import net.owl.context.spring.EventPublisherFactoryBean;
import net.owl.log.LogRepository;
import net.owl.mock.action.TestActionRepository;
import net.owl.mock.log.TestLogRepository;
import net.owl.publish.EventPublisher;
import net.owl.support.spring.SpringBeanActionLoader;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@Configurable
@ImportResource(value="classpath:net/owl/conf/testContext-common.xml")
public class TestConfiguration {

	@Bean
	public ActionRepository actionRepository() {
		return new TestActionRepository();
	}
	
	@Bean
	public ActionLoader actionLoader() {
		return new SpringBeanActionLoader();
	}
	
	@Bean
	public LogRepository<?> logRepository() {
		return new TestLogRepository();
	}
	
	@Bean
	public FactoryBean<EventPublisher> publisher() {
		return new EventPublisherFactoryBean();
	}
}
