package net.owl.action.meta;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.owl.event.EventType;

public class ActionDefinition {

	private int actionKey;
	
	private String actionId;
	
	private String actionName;
	
	private Set<EventType> triggers;
	
	private List<ActionProperty> properties;

	public ActionDefinition(String actionId) {
		this.actionId = actionId;
		this.actionKey = actionId.hashCode();
	}
	
	public int getActionKey() {
		return actionKey;
	}

	public String getActionId() {
		return actionId;
	}

	public String getActionName() {
		return actionName;
	}

	public Set<EventType> getTriggers() {
		return triggers;
	}

	public List<ActionProperty> getProperties() {
		return properties;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	public void addTrigger(EventType trigger) {
		if (this.triggers == null) {
			triggers = new HashSet<>();
		}
		
		this.triggers.add(trigger);
	}
	
	public void setProperties(List<ActionProperty> properties) {
		this.properties = properties;
	}
}
