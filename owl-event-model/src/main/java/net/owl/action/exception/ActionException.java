package net.owl.action.exception;

public class ActionException extends RuntimeException {

	private static final long serialVersionUID = 5930197393196750268L;

	public ActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActionException(String message) {
		super(message);
	}
	
	public ActionException(Throwable cause) {
		super(MessageBuilder.message(cause));
	}

	private static class MessageBuilder {
		static String message(Throwable t) {
			StringBuilder message = new StringBuilder();
			message.append("action failed..! [message : ")
				.append(t.getMessage())
				.append(", cause : ")
				.append(t.getClass().getName())
				.append("]");
			
			return message.toString();
		}
	}
}
