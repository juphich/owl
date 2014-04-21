package net.owl.mock.action;

import net.owl.annotation.Action;
import net.owl.log.EventLog;
import net.owl.log.GenericLogWriter;
import net.owl.log.LogWriter;

@Action(triggers = {"load"})
public class ChildAction extends MockSuper {

	public ChildAction() {
		super("child");
	}

	@Override
	protected LogWriter<? extends EventLog> process(String data) {
		System.out.println("child..... " + data);
		return new GenericLogWriter().action(getName());
	}

}
