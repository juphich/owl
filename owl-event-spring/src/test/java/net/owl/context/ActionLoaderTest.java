package net.owl.context;

import static org.junit.Assert.*;

import java.util.Collection;

import net.owl.action.Action;
import net.owl.context.ActionLoader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class ActionLoaderTest {

	@Autowired ActionLoader actionLoader;
	
	@Test
	public void testSpringActionLoader() {
		Collection<Action<?>> actions = actionLoader.load();
		
		assertEquals(3, actions.size());
	}
}