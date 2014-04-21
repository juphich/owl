package net.owl.action;

import net.owl.action.ServiceInvokeAction;
import net.owl.log.EventLog;

import org.junit.Test;

public class ServiceInvokeActionTest {

	@Test
	public void testCreateAction() {
		ServiceInvokeAction<Param> action = new ServiceInvokeAction<Param>();
		action.setTarget(new SampleService());
		action.setOperation("test");
		
		Param param = new Param("jupic", "test...");
		
		EventLog log = action.doAction(param);
		
		System.out.println(log.getAction());
	}	
}

/**
 * 테스트용 event 메타데이터
 * 
 * @author jupic-dev
 */
class Param {
	String name;
	String target;
	
	Param(String name, String target) {
		this.name = name;
		this.target = target;
	}

	@Override
	public String toString() {
		return "{name:\""+name+"\", target:\""+target+"\"}";
	}
}

/**
 * 테스트용 서비스
 * @author jupic-dev
 */
class SampleService {
	public void test(Param param) {
		System.out.println("name : " + param.name + " / target : " + param.target);
	}
}
