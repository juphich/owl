package net.owl.action.exception;

public class ServiceInvocationException extends ActionException {
	
	private static final long serialVersionUID = -9169170815023514859L;

	public ServiceInvocationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceInvocationException(String message) {
		super(message);
	}

	public ServiceInvocationException(Throwable cause) {
		super(cause);
	}
}
