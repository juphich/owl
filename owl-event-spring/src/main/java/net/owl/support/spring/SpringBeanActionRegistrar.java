package net.owl.support.spring;

import java.util.Collection;

import net.owl.action.Action;
import net.owl.action.ActionRepository;
import net.owl.context.ActionLoader;
import net.owl.event.EventType;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.Lifecycle;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.Assert;

@SuppressWarnings("rawtypes")
public class SpringBeanActionRegistrar implements Lifecycle, ApplicationListener, ApplicationContextAware, InitializingBean {

	private ActionRepository actionRepository;
	private ActionLoader actionLoader;
	private Class<? extends EventType> eventEnumType;
	
	private ApplicationContext applicationContext;
	
	private Object lifecycleMonitor = new Object();
	private volatile boolean running = false;
	
	
	public void setActionLoader(ActionLoader actionLoader) {
		this.actionLoader = actionLoader;
	}

	public void setActionRepository(ActionRepository actionRepository) {
		this.actionRepository = actionRepository;
	}

	public void setEventEnumType(Class<? extends EventType> eventEnumType) {
		if (!eventEnumType.isEnum()) {
			throw new IllegalArgumentException(eventEnumType + " is not event enum.");
		}
		
		this.eventEnumType = eventEnumType;
	}

	/**
	 * @see InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(actionRepository, "ActionRepository must be set!!");
		Assert.notNull(actionLoader, "ActionLoader must be set!!");
		Assert.notNull(eventEnumType, "EventType must be set!!");
	}

	/**
	 * @see ApplicationContextAware#setApplicationContext()
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * @see ApplicationContextAware#onApplicationEvent()
	 */
	@Override
	public final void onApplicationEvent(ApplicationEvent event) {
		if (event.getSource() == applicationContext) {
			if (event instanceof ContextRefreshedEvent) {
				start();
			}
			else if (event instanceof ContextClosedEvent) {
				stop();
			}
		}
	}

	/**
	 * @see Lifecycle#start()
	 */
	@Override
	public void start() {
		synchronized (this.lifecycleMonitor) {
			if (running) {
				return;
			}
			
			Collection<Action<?>> actions = null;
			
			if (actionLoader instanceof SpringBeanActionLoader) {
				actions = ((SpringBeanActionLoader) actionLoader).load(applicationContext);
			} else {
				actions = actionLoader.load();
			}
			
			for (Action<?> action : actions) {
				actionRepository.register(action);
			}
			
			running = true;
		}
	}

	/**
	 * @see Lifecycle#stop()
	 */
	@Override
	public void stop() {
		synchronized (this.lifecycleMonitor) {
			actionRepository.clear();
			running = false;
		}
	}

	/**
	 * @see Lifecycle#isRunning()
	 */
	@Override
	public boolean isRunning() {
		synchronized (this.lifecycleMonitor) {
			return running;
		}
	}
}
