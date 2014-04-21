package net.owl.log;

import java.util.List;

public interface LogRepository<L extends EventLog> {

	void write(L log);
	
	List<L> search();
}
