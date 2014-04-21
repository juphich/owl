package net.owl.mock.event;

import net.owl.event.AbstractEvent;
import net.owl.event.EventType;
import net.owl.mock.event.TestEventType;

public class CloseEvent<S> extends AbstractEvent<S> {

	public CloseEvent(S source) {
		super(source);
	}

	@Override
	public EventType type() {
		return TestEventType.close;
	}
}
