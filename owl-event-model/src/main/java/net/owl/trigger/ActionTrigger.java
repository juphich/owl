package net.owl.trigger;

import java.util.List;

import net.owl.action.Action;
import net.owl.action.ActionRepository;
import net.owl.action.exception.ActionException;
import net.owl.event.Event;
import net.owl.log.EventLog;
import net.owl.log.LogRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ActionTrigger<P> implements Trigger {

	protected ActionRepository actionRepository;
	protected LogRepository<? extends EventLog> logRepository;

	private Logger log = LoggerFactory.getLogger(getClass());
	
	protected void setActionRepository(ActionRepository actionRepository) {
		this.actionRepository = actionRepository;
	}

	protected void setLogRepository(LogRepository<? extends EventLog> logRepository) {
		this.logRepository = logRepository;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void trigger(Event<?> event) {
		List<Action<?>> actions = actionRepository.find(event.type());
		for (Action<?> action : actions) {
			try {
				EventLog eventLog = notifyActions((Action<P>)action, (P)event.source());
				if (log.isDebugEnabled()) {
					log.info("[{} event : {}] action called.", event.type().getName(), action.getName());
				}
				
				writeLog(eventLog);
			} catch (ActionException e) {
				log.error("[{} event : {}] {} ", new Object[]{event.type().getName(), action.getName(), e.getMessage()});
				if (log.isDebugEnabled()) {
					log.error("trace : ", e);
				}
			}
		}
	}
	
	private EventLog notifyActions(Action<? super P> action, P data) {
		return action.doAction(data);
	}
	
	@SuppressWarnings("unchecked")
	private void writeLog(EventLog log) {
		if (logRepository != null) {
			((LogRepository<? super EventLog>)logRepository).write(log);
		}
	}
}
