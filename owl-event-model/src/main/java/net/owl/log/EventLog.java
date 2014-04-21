package net.owl.log;

import java.util.Date;

import net.owl.event.Event;

public interface EventLog {
	String getAction();
	
	Date getDate();
	
	Event<?> getEvent();
}
