package net.owl.action;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import net.owl.action.AbstractAction;
import net.owl.action.Action;
import net.owl.action.meta.ActionDefinition;
import net.owl.action.meta.ActionMetaUtils;
import net.owl.action.meta.ActionProperty;
import net.owl.log.EventLog;
import net.owl.log.LogWriter;
import net.owl.mock.event.TestEventType;

import org.junit.Test;

public class ActionMetadataTest {

	@Test
	public void actionMetaTest() {
		Action<String> testAction = new TestAction();
		
		ActionDefinition meta = ActionMetaUtils.extractMeta(testAction, TestEventType.class);
		
		assertThat(meta.getActionKey(), is(TestAction.class.getName().hashCode()));
		assertThat(meta.getActionName(), is("Test"));
		//assertThat(meta.getTriggers(), contain(TestEventType.join));
	}
	
	@Test
	public void actionPropertyTest() {
		Action<String> testAction = new TestAction();
		
		List<ActionProperty> properties = ActionMetaUtils.extractProperties(testAction);
		
		assertEquals(3, properties.size());
	}
	
	@Test
	public void keyHashingTest() {
		String id = "SimpleAction";
		
		int hash = ActionMetaUtils.keyHash(id);
		
		assertThat(hash, is(109));
	}
}

@net.owl.annotation.Action(name="Test", triggers={"join"})
class TestAction extends AbstractAction<String> {

	@net.owl.annotation.ActionProperty("pterm") private int term;
	@net.owl.annotation.ActionProperty(name="tname", init="test") private String type;
	@net.owl.annotation.ActionProperty(init="2014-04-17 12:11:11") private Date time;
	
	public TestAction() {
		super("Test");
	}

	@Override
	protected LogWriter<? extends EventLog> process(String data) {
		return null;
	}	
}
