package net.owl.action.exception;

public class WebApiInvocationException extends ActionException {

	private static final long serialVersionUID = -8979269978428405590L;

	public WebApiInvocationException(String message, String uri, Throwable cause) {
		super(MessageBuilder.message(message, uri, cause), cause);
	}

	public WebApiInvocationException(String message, String uri) {
		super(MessageBuilder.message(message, uri));
	}
	
	public WebApiInvocationException(Throwable cause) {
		super(cause);
	}

	private static class MessageBuilder {
		static String message(String message, String uri) {
			return message(message, uri, null);
		}
		
		static String message(String message, String uri, Throwable t) {
			StringBuilder msg = new StringBuilder();
			msg.append(message).append(" [uri : ").append(uri);
			
			if (t != null) {
				msg.append(", cuase : ").append(t.getMessage());
			}
			msg.append("]");
			
			return msg.toString();
		}
	}
}
