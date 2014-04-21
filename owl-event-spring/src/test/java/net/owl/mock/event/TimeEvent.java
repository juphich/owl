package net.owl.mock.event;

import net.owl.event.AbstractEvent;
import net.owl.event.EventType;
import net.owl.mock.event.TestEventType;

public class TimeEvent<S> extends AbstractEvent<S> {

	public TimeEvent(S source) {
		super(source);
	}

	@Override
	public EventType type() {
		return TestEventType.time;
	}
}
