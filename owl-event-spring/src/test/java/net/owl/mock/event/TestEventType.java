package net.owl.mock.event;

import net.owl.event.EventType;

public enum TestEventType implements EventType {
	load, close, time;

	@Override
	public String getName() {
		return this.name();
	}
}
