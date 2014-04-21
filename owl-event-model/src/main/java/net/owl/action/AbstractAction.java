package net.owl.action;

import net.owl.log.EventLog;
import net.owl.log.LogWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAction<P> implements Action<P> {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	private String name;

	public AbstractAction(String name) {
		this.name = name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public final EventLog doAction(P data) {
		LogWriter<? extends EventLog> writer = process(data);
		
		return writer.write();
	}
	
	protected abstract LogWriter<? extends EventLog> process(P data);
}
