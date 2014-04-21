package net.owl.mock.log;

import java.util.List;

import net.owl.log.GenericLog;
import net.owl.log.LogRepository;

public class TestLogRepository implements LogRepository<GenericLog> {

	@Override
	public void write(GenericLog log) {
		System.out.println("log ; " + log.getAction());
	}

	@Override
	public List<GenericLog> search() {
		return null;
	}
}
