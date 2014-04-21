package net.domain;

public class Repository {

	private static RepositoryContext context;
	
	public static <R> R find(Class<?> type) {
		if (context != null) {
			return context.get(type);
		} else {
			throw new IllegalArgumentException("no such repository...");
		}
	}
	
	public static <R> void register(Class<?> type, R repository) {
		if (context == null) {
			context = new RepositoryContext();
		}
		
		if (context.hasRepository(type)) {
			throw new IllegalArgumentException("");
		}
		
		context.add(type, repository);
	}
	
	public static void remove(Class<?> type) {
		if (context != null) {
			context.remove(type);
		}
	}
}
