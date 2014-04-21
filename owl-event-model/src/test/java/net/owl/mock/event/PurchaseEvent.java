package net.owl.mock.event;

import net.owl.event.AbstractEvent;
import net.owl.event.EventType;

public class PurchaseEvent<S> extends AbstractEvent<S> {

	public PurchaseEvent(S source) {
		super(source);
	}

	@Override
	public EventType type() {
		return TestEventType.purchase;
	}
}
