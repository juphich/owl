package net.owl.mock.event;

import net.owl.event.EventType;

public enum TestEventType implements EventType {
	join, purchase, login, mission, sustain;

	@Override
	public String getName() {
		return this.name();
	}
}
