package exerciselogger.tasks;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerConsoleTask implements ILoggerTask {

	private String message;
	private Logger logger;
	private ConsoleHandler ch;

	public LoggerConsoleTask(String message, Logger logger, ConsoleHandler ch) {
		this.message = message;
		this.logger = logger;
		this.ch = ch;
	}

	public void execute() { 
		Handler[] handlers = this.logger.getHandlers();

		for (Handler handler : handlers)
			this.logger.removeHandler(handler);

		this.logger.addHandler(this.ch);
		this.logger.log(Level.INFO, this.message);
	}
}