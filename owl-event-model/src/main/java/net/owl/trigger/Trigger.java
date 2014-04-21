package net.owl.trigger;

import net.owl.event.Event;

public interface Trigger {

	boolean support(Class<?> type);

	void trigger(Event<?> event);
}