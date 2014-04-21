package net.owl.event;

public abstract class AbstractEvent<S> implements Event<S> {

	private S source;
	
	public AbstractEvent(S source) {
		this.source = source;
	}
	
	@Override
	public S source() {
		return source;
	}

}
