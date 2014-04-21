package net.owl.mock.event;

import net.owl.event.AbstractEvent;
import net.owl.event.EventType;
import net.owl.mock.event.TestEventType;

public class LoadEvent<S> extends AbstractEvent<S> {

	public LoadEvent(S source) {
		super(source);
	}

	@Override
	public EventType type() {
		return TestEventType.load;
	}
}
