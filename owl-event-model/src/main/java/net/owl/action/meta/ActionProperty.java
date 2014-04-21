package net.owl.action.meta;

public class ActionProperty {

	private int propertyKey;
	
	private int actionKey;
	
	private String propertyName;
	
	private String propertyValue;

	public ActionProperty(String name) {
		this.propertyName = name;
		this.propertyKey = name.hashCode();
	}
	
	public int getActionKey() {
		return actionKey;
	}

	public int getPropertyKey() {
		return propertyKey;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	void setActionKey(int actionKey) {
		this.actionKey = actionKey;
	}
}
