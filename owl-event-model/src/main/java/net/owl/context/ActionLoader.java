package net.owl.context;

import java.util.Collection;

import net.owl.action.Action;

public interface ActionLoader {

	Collection<Action<?>> load();

}
