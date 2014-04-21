package net.owl.trigger;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.domain.Repository;
import net.owl.action.Action;
import net.owl.action.ActionRepository;
import net.owl.event.Event;
import net.owl.event.EventType;
import net.owl.log.EventLog;
import net.owl.log.LogRepository;
import net.owl.mock.event.JoinEvent;
import net.owl.mock.trigger.JoinTrigger;
import net.owl.trigger.Trigger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TriggerTest {

	@Mock private ActionRepository actionRepository;
	@Mock private LogRepository<?> logRepository;
	@Mock private Action<Object> action;
	
	@Before
	public void prepare() {
		List<Action<?>> actions = new ArrayList<Action<?>>();
		actions.add(action);
		when(actionRepository.find(any(EventType.class))).thenReturn(actions);
		
		Repository.register(Action.class, actionRepository);
		Repository.register(EventLog.class, logRepository);
	}
	
	@Test
	public void testTrigger() {
		Trigger trigger = new JoinTrigger<Join>();
		
		Event<Join> event = new JoinEvent<Join>(new Join());
		trigger.trigger(event);
		
		verify(action).doAction(event.source());
	}
	
	/**
	 * 테스트용 Join 이벤트 메타데이터
	 * 
	 * @author jupic-dev
	 */
	class Join {
		Date registered;
		String id;
	}
}
