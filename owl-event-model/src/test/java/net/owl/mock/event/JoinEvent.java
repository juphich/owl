package net.owl.mock.event;

import net.owl.event.AbstractEvent;
import net.owl.event.EventType;

public class JoinEvent<S> extends AbstractEvent<S> {

	public JoinEvent(S source) {
		super(source);
	}

	@Override
	public EventType type() {
		return TestEventType.join;
	}
}
