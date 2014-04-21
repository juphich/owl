package net.owl.context.spring;

import java.util.Map;

import net.owl.publish.EventPublisher;
import net.owl.publish.GenericEventPublisher;
import net.owl.trigger.Trigger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class EventPublisherFactoryBean implements FactoryBean<EventPublisher>,
			InitializingBean, 
			ApplicationContextAware {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApplicationContext applicationContext;
	
	@Override
	public EventPublisher getObject() throws Exception {
		EventPublisher publisher = new GenericEventPublisher();
		
		registerTriggers(publisher);
		
		return publisher;
	}

	@Override
	public Class<?> getObjectType() {
		return GenericEventPublisher.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}
	
	private void registerTriggers(EventPublisher publisher) {
		Map<String, Object> triggers = this.applicationContext.getBeansWithAnnotation(net.owl.annotation.Trigger.class);
		
		for (Object t : triggers.values()) {
			if (!(t instanceof Trigger)) {
				throw new IllegalArgumentException();
			}
			
			Trigger trigger = (Trigger) t;
			publisher.registerTrigger(trigger);
			log.info("{} trigger registered", trigger.getClass().getSimpleName());
		}
	}
}