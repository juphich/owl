package net.owl.mock.event;

import net.owl.event.AbstractEvent;
import net.owl.event.EventType;

public class LoginEvent<S> extends AbstractEvent<S> {

	public LoginEvent(S source) {
		super(source);
	}

	@Override
	public EventType type() {
		return TestEventType.login;
	}
}
