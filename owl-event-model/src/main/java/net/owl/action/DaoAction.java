package net.owl.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import net.owl.action.exception.ActionException;
import net.owl.log.EventLog;
import net.owl.log.GenericLogWriter;
import net.owl.log.LogWriter;

public class DaoAction<P> extends AbstractAction<P> {

	private DataSource dataSource;
	private String actionQuery;
	
	public DaoAction() {
		super("dao action");
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setActionQuery(String actionQuery) {
		this.actionQuery = actionQuery;
	}

	@Override
	protected LogWriter<? extends EventLog> process(P data) {
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(actionQuery);
			) {
			
			conn.setAutoCommit(false);
			statement.executeUpdate();
			
			StringBuilder action = new StringBuilder();
			action.append("action name : ").append(getName()).append(", query : ").append(actionQuery);
			
			return new GenericLogWriter().action(action.toString());
		} catch (SQLException e) {
			throw new ActionException(e);
		}
	}
}
