package exerciselogger.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

public class JobLoggerDao {

	private String userName;
	private String password;
	private String url;

	public JobLoggerDao(Map<String, String> dbParams) {
		this.userName = dbParams.get("userName");
		this.password = dbParams.get("password");
		this.url = dbParams.get("url");
	}

	public void insertLog(String messageText) {
		try (Connection connection = DriverManager.getConnection(url, userName, password);
				PreparedStatement cs = connection.prepareStatement("INSERT INTO LOGLINE (message) VALUES (?)");) {
			cs.setString(1, messageText);
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}