package net.owl.mock.trigger;

import net.owl.annotation.Trigger;
import net.owl.mock.event.CloseEvent;
import net.owl.trigger.ActionTrigger;

@Trigger
public class CloseTrigger<P> extends ActionTrigger<P> {

	@Override
	public boolean support(Class<?> type) {
		return CloseEvent.class.isAssignableFrom(type);
	}
}
