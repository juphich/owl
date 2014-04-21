package net.domain;

import java.util.concurrent.ConcurrentHashMap;

public class RepositoryContext {

	private ConcurrentHashMap<Class<?>, Object> repositoryMap = new ConcurrentHashMap<Class<?>, Object>();
	
	@SuppressWarnings("unchecked")
	public <R> R get(Class<?> type) {
		return (R) repositoryMap.get(type);
	}
	
	public <R> void add(Class<?> type, R repository) {
		repositoryMap.put(type, repository);
	}

	public boolean hasRepository(Class<?> type) {
		return repositoryMap.containsKey(type);
	}

	public void remove(Class<?> type) {
		repositoryMap.remove(type);
	}
}
