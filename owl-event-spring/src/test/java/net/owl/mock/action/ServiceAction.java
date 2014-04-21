package net.owl.mock.action;

import net.owl.action.ServiceInvokeAction;
import net.owl.annotation.Action;

import org.springframework.context.annotation.Scope;

@Action(triggers={"load"}) @Scope("prototype")
public class ServiceAction extends ServiceInvokeAction<String> {
	
	public ServiceAction() {
		this.setName("ServiceAction");
		this.setOperation("test");
		this.setTarget(new SimpleService());
	}
	
	public class SimpleService {
		public void test(String obj) {
			System.out.println("run simple service : " + obj);
		}
	}
}