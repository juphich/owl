package net.owl.mock.action;

import net.owl.action.AbstractAction;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public abstract class MockSuper extends AbstractAction<String> {

	public MockSuper(String name) {
		super(name);
	}

}
