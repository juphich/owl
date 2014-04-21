package net.owl.action;

import net.owl.log.EventLog;
import net.owl.log.LogWriter;

public class TemplateAction<P> extends AbstractAction<P> {

	public TemplateAction() {
		super("template action");
	}

	@Override
	protected LogWriter<? extends EventLog> process(P data) {
		return null;
	}
}
