package net.owl.publish;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import net.owl.mock.event.JoinEvent;
import net.owl.publish.EventPublisher;
import net.owl.publish.GenericEventPublisher;
import net.owl.trigger.Trigger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventPublisherTest {

	@Mock private Trigger joinTrigger;
	
	@Test
	public void testEventPublisher() {
		when(joinTrigger.support(JoinEvent.class)).thenReturn(true);
		
		EventPublisher publisher = new GenericEventPublisher();
		publisher.registerTrigger(joinTrigger);
		
		publisher.notifyEvent(new JoinEvent<Object>(null));
		
		verify(joinTrigger).trigger(any(JoinEvent.class));
	}
}
