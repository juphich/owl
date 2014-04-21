package net.owl.mock.trigger;

import net.domain.Repository;
import net.owl.action.Action;
import net.owl.action.ActionRepository;
import net.owl.log.EventLog;
import net.owl.log.LogRepository;
import net.owl.mock.event.JoinEvent;
import net.owl.trigger.ActionTrigger;

public class JoinTrigger<P> extends ActionTrigger<P> {

	public JoinTrigger() {
		this.setActionRepository((ActionRepository) Repository.find(Action.class));
		this.setLogRepository((LogRepository<?>) Repository.find(EventLog.class));
	}
	
	@Override
	public boolean support(Class<?> type) {
		return JoinEvent.class.isAssignableFrom(type);
	}
}