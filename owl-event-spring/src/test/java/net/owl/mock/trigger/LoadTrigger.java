package net.owl.mock.trigger;

import net.owl.action.ActionRepository;
import net.owl.annotation.Trigger;
import net.owl.log.LogRepository;
import net.owl.mock.event.LoadEvent;
import net.owl.trigger.ActionTrigger;

import org.springframework.beans.factory.annotation.Autowired;

@Trigger
public class LoadTrigger<P> extends ActionTrigger<P> {

	@Autowired 
	public void setTestActionRepository(ActionRepository actionRepository) {
		super.setActionRepository(actionRepository);
	}
	
	@Autowired
	public void setTestLogRepository(LogRepository<?> logRepository) {
		super.setLogRepository(logRepository);
	}
	
	@Override
	public boolean support(Class<?> type) {
		return LoadEvent.class.isAssignableFrom(type);
	}
}
