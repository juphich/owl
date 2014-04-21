package net.owl.mock.event;

import net.owl.event.AbstractEvent;
import net.owl.event.EventType;

public class MissionEvent<S> extends AbstractEvent<S> {

	public MissionEvent(S source) {
		super(source);
	}

	@Override
	public EventType type() {
		return TestEventType.mission;
	}
}
