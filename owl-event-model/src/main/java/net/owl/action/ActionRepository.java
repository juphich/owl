package net.owl.action;

import java.util.List;

import net.owl.event.EventType;

public interface ActionRepository {

	List<Action<?>> find(EventType type);

	void register(Action<?> action);
	
	void clear();
}
