package net.owl.event;

public interface Event<S> {

	EventType type();
	
	S source();
}
