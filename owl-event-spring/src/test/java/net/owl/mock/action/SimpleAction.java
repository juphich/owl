package net.owl.mock.action;

import net.owl.action.Action;
import net.owl.log.EventLog;
import net.owl.log.GenericLogWriter;

@net.owl.annotation.Action(triggers = {"load"})
public class SimpleAction implements Action<String> {

	@Override
	public String getName() {
		return "SimpleAction";
	}

	@Override
	public EventLog doAction(String data) {
		System.out.println("do action.... - " + data);
		return new GenericLogWriter().action(getName()).write();
	}
}