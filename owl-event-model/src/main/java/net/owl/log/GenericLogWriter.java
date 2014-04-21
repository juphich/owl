package net.owl.log;

import net.owl.event.Event;

public class GenericLogWriter implements LogWriter<GenericLog> {

	private Event<?> event;
	private String action;
	
	public GenericLogWriter event(Event<?> event) {
		this.event = event;
		return this;
	}

	public GenericLogWriter action(String action) {
		this.action = action;
		return this;
	}

	@Override
	public GenericLog write() {
		return new GenericLog(event, action);
	}

}
