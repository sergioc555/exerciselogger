package exerciselogger.tasks;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerFileTask implements ILoggerTask {

	private String message;
	private Logger logger;
	private FileHandler fh;

	public LoggerFileTask(String message, Logger logger, FileHandler fh) {
		this.message = message;
		this.logger = logger;
		this.fh = fh;
	}

	public void execute() {
		Handler[] handlers = this.logger.getHandlers();

		for (Handler handler : handlers)
			this.logger.removeHandler(handler);

		this.logger.addHandler(this.fh);
		this.logger.log(Level.INFO, message);
	}
}