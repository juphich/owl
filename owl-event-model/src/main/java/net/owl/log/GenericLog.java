package net.owl.log;

import java.util.Date;

import net.owl.event.Event;

public class GenericLog implements EventLog {

	private Event<?> event;
	private Date time;
	private String action;
	
	public GenericLog(Event<?> event, String action) {
		this.event = event;
		this.action = action;
		this.time = new Date(System.currentTimeMillis());
	}
	
	@Override
	public String getAction() {
		return action;
	}

	@Override
	public Date getDate() {
		return time;
	}

	@Override
	public Event<?> getEvent() {
		return event;
	}
}