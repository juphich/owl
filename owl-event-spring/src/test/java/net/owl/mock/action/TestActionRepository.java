package net.owl.mock.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.owl.action.Action;
import net.owl.action.ActionRepository;
import net.owl.action.meta.ActionDefinition;
import net.owl.action.meta.ActionMetaUtils;
import net.owl.event.EventType;
import net.owl.mock.event.TestEventType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class TestActionRepository implements ActionRepository {

	@Autowired private ApplicationContext applicationContext;
	
	private Map<EventType, List<Class<?>>> actionMap;
	
	public TestActionRepository() {
		actionMap = new HashMap<>();
	}
	
	@Override
	public List<Action<?>> find(EventType type) {
		List<Action<?>> actions = new ArrayList<>();
		
		List<Class<?>> types = actionMap.get(type);
		for (Class<?> t : types) {
			actions.add((Action<?>) applicationContext.getBean(t));
		}
		
		return actions;
	}

	@Override
	public void register(Action<?> action) {
		ActionDefinition meta = ActionMetaUtils.extractMeta(action, TestEventType.class);
		
		for (EventType event : meta.getTriggers()) {			
			List<Class<?>> group = actionMap.get(event);
			if (group == null) {
				group = new ArrayList<Class<?>>();
			}
			group.add(action.getClass());
			actionMap.put(event, group);
		}
	}

	@Override
	public void clear() {
		actionMap = new HashMap<>();
	}
}
