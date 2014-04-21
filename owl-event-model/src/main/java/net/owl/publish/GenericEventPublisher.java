package net.owl.publish;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.owl.event.Event;
import net.owl.trigger.Trigger;

public class GenericEventPublisher implements EventPublisher {

	private Set<Trigger> triggers = new HashSet<Trigger>();
	
	@Override
	public void registerTrigger(Trigger trigger) {
		triggers.add(trigger);
	}

	@Override
	public void abandonTrigger(Trigger trigger) {
		triggers.remove(trigger);
	}

	@Override
	public void notifyEvent(Event<?> event) {
		for (Trigger t : triggers) {
			if (t.support(event.getClass())) {
				t.trigger(event);
			}
		}
	}

	@Override
	public Collection<Trigger> getTriggers() {
		return Collections.unmodifiableCollection(triggers);
	}
}
