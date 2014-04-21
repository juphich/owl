package net.owl.action;

import net.owl.log.EventLog;

public interface Action<P> {

	String getName();
	
	EventLog doAction(P data);
}
