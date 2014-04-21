package net.owl.action;

import net.owl.action.Action;
import net.owl.action.RestApiAction;
import net.owl.log.EventLog;

import org.junit.Test;

public class RestApiActionTest {

	@Test
	public void test() {
		Action<Param> action = new RestApiAction<Param>("http://api.ana.net/user/{userNum}/withdraw/{time}");
		
		EventLog log = action.doAction(new Param(497244, "20140401", "varf"));
		System.out.println(log.getAction());
	}
	
	public class Param {
		private int userNum;
		private String time;
		private String va;
		
		Param(int userNum, String time, String va) {
			this.userNum = userNum;
			this.time = time;
			this.va = va;
		}
		
		public int getUserNum() {
			return userNum;
		}
		public String getTime() {
			return time;
		}
		public String getVa() {
			return va;
		}
	}
}