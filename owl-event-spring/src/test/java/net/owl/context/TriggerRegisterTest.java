package net.owl.context;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import java.util.Collection;

import net.owl.mock.event.LoadEvent;
import net.owl.publish.EventPublisher;
import net.owl.trigger.Trigger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class TriggerRegisterTest {

	@Autowired private EventPublisher publisher;
	
	@Test
	public void testEventPublisher() {
		Collection<Trigger> triggers = publisher.getTriggers();
		
		assertThat(triggers.size(), is(3));
		
		publisher.notifyEvent(new LoadEvent<String>("load event called..."));
	}
}
