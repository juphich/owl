package net.owl.mock.trigger;

import net.domain.Repository;
import net.owl.action.Action;
import net.owl.action.ActionRepository;
import net.owl.log.EventLog;
import net.owl.log.LogRepository;
import net.owl.mock.event.PurchaseEvent;
import net.owl.trigger.ActionTrigger;

public class PurchaseTrigger<P> extends ActionTrigger<P> {

	public PurchaseTrigger() {
		this.setActionRepository((ActionRepository) Repository.find(Action.class));
		this.setLogRepository((LogRepository<?>) Repository.find(EventLog.class));
	}
	
	@Override
	public boolean support(Class<?> type) {
		return PurchaseEvent.class.isAssignableFrom(type);
	}
}
