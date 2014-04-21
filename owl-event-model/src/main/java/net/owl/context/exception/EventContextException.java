package net.owl.context.exception;

public class EventContextException extends RuntimeException {

	private static final long serialVersionUID = -391538757840557338L;

	public EventContextException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventContextException(String message) {
		super(message);
	}

	public EventContextException(Throwable cause) {
		super(MessageBuilder.message(cause), cause);
	}
	
	private static class MessageBuilder {
		private static String message(Throwable cause) {
			StringBuilder message = new StringBuilder();
			message.append("cuase : [").append(cause.getClass().getName()).append("]")
				.append(", message : [").append(cause.getMessage()).append("]")
				.append("\n").append(cause.getStackTrace()[0]);
			
			return message.toString();
		}
	}
}
