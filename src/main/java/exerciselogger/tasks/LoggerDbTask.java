package exerciselogger.tasks;

import java.util.Map;

import exerciselogger.dao.JobLoggerDao;

public class LoggerDbTask implements ILoggerTask {

	private String message;
	private Map<String, String> dbParams;

	public LoggerDbTask(String message, Map<String, String> dbParams) {
		this.message = message;
		this.dbParams = dbParams;
	}

	public void execute() {
		JobLoggerDao jobLoggerDao = new JobLoggerDao(dbParams);
		jobLoggerDao.insertLog(this.message);
	}
}