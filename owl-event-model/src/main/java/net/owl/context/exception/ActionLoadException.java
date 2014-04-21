package net.owl.context.exception;

public class ActionLoadException extends EventContextException {

	private static final long serialVersionUID = -5569543668393221708L;

	public ActionLoadException(String message) {
		super(message);
	}

	public ActionLoadException(Throwable cause) {
		super(cause);
	}
}
