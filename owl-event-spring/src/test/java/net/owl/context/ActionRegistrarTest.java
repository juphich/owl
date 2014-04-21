package net.owl.context;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import java.util.List;

import net.owl.action.Action;
import net.owl.action.ActionRepository;
import net.owl.mock.event.TestEventType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class ActionRegistrarTest {

	@Autowired private ActionRepository actionRepository;
	
	@Test
	public void testActionRegistrar() {
		List<Action<?>> actions = actionRepository.find(TestEventType.load);
		
		assertThat(actions.size(), is(3));
		
		System.out.println(actions);
		System.out.println(actionRepository.find(TestEventType.load));
	}
}
