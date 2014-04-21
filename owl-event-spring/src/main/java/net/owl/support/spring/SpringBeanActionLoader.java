package net.owl.support.spring;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.owl.action.Action;
import net.owl.context.ActionLoader;
import net.owl.context.exception.ActionLoadException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanActionLoader implements ActionLoader, ApplicationContextAware, InitializingBean {

	private ApplicationContext context;
	
	/**
	 * @see ActionLoader#load()
	 */
	@Override
	public Collection<Action<?>> load() {
		return load(this.context);
	}
	
	public Collection<Action<?>> load(ApplicationContext applicationContext) {
		Map<String, Object> actions = applicationContext.getBeansWithAnnotation(net.owl.annotation.Action.class);
		
		Set<Action<?>> results = new HashSet<>();
		
		for (Entry<String, Object> entry : actions.entrySet()) {
			Object value = entry.getValue();
			
			net.owl.annotation.Action annotation = 
					value.getClass().getAnnotation(net.owl.annotation.Action.class);
			
			String name = !annotation.value().isEmpty() ? annotation.value() : 
							!annotation.name().isEmpty() ? annotation.name() : entry.getKey();
							
			if (!(value instanceof Action)) {
				throw new ActionLoadException("it's not a valied action type.. - action [" + name + "]");
			}
			
			results.add((Action<?>)value);
		}
		
		return results;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	/**
	 * @see ApplicationContextAware#setApplicationContext()
	 */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}
}
