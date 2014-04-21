package net.owl.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.owl.action.exception.ServiceInvocationException;
import net.owl.log.EventLog;
import net.owl.log.GenericLogWriter;
import net.owl.log.LogWriter;

public class ServiceInvokeAction<P> extends AbstractAction<P> {

	private Object target;
	private String operation;
	
	public ServiceInvokeAction() { 
		super("service action");
	}
	
	public ServiceInvokeAction(Object target) {
		super("service action");
		this.target = target;
	}
	
	public void setTarget(Object target) {
		this.target = target;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	protected LogWriter<? extends EventLog> process(P data) {
		checkValidation();
		invoke(data);
		
		StringBuilder action = new StringBuilder();
		action.append("action name : ").append(getName()).append(" / ")
			.append("invoke : ").append(target.getClass())
			.append(".").append(operation).append("() / ")
			.append("parameter : ").append(data);
		
		return new GenericLogWriter().action(action.toString());
	}
	
	private void invoke(P data) {
		Class<?> type = target.getClass();
		
		try {
			Method method = type.getMethod(operation, new Class[]{data.getClass()});
			
			method.invoke(target, data);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new ServiceInvocationException(e);
		} catch (IllegalAccessException e) {
			throw new ServiceInvocationException(e);
		} catch (IllegalArgumentException e) {
			throw new ServiceInvocationException(e);
		} catch (InvocationTargetException e) {
			throw new ServiceInvocationException(e);
		}
	}
	
	private void checkValidation() {
		if (target == null) {
			throw new ServiceInvocationException("target is empty");
		}
		
		if (operation == null || operation.isEmpty()) {
			throw new ServiceInvocationException("operation is empty");
		}
	}
}
