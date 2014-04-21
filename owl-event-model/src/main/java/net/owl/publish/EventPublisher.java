package net.owl.publish;

import java.util.Collection;

import net.owl.event.Event;
import net.owl.trigger.Trigger;

public interface EventPublisher {

	void registerTrigger(Trigger trigger);
	
	void abandonTrigger(Trigger tirgger);
	
	void notifyEvent(Event<?> event);

	Collection<Trigger> getTriggers();
}
