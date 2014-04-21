package net.owl.mock.trigger;

import net.owl.annotation.Trigger;
import net.owl.mock.event.TimeEvent;
import net.owl.trigger.ActionTrigger;

@Trigger
public class TimeTrigger<P> extends ActionTrigger<P> {

	@Override
	public boolean support(Class<?> type) {
		return TimeEvent.class.isAssignableFrom(type);
	}
}
