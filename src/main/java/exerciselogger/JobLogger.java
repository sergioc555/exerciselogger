package exerciselogger;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import exerciselogger.exceptions.MyLogException;
import exerciselogger.model.OptionsLogger;
import exerciselogger.tasks.InvokerLoggerTasks;
import exerciselogger.tasks.LoggerConsoleTask;
import exerciselogger.tasks.LoggerDbTask;
import exerciselogger.tasks.LoggerFileTask;

public class JobLogger {
	OptionsLogger options;
	private Map<String, String> dbParams;
	private Logger logger;
	private ConsoleHandler ch;
	private FileHandler fh;

	public static final Integer VALIDATE_NOT_LOG = -1;
	public static final Integer VALIDATE_OK = 1;

	public enum TypesLogs {
		TYPE_MESSAGE, TYPE_WARNING, TYPE_ERROR
	}

	private static final EnumMap<TypesLogs, String> DESCRIPTION_TYPES = new EnumMap<>(
			TypesLogs.class);

	static {
		DESCRIPTION_TYPES.put(TypesLogs.TYPE_MESSAGE, "[MESSAGE]");
		DESCRIPTION_TYPES.put(TypesLogs.TYPE_WARNING, "[WARNING]");
		DESCRIPTION_TYPES.put(TypesLogs.TYPE_ERROR, "[ERROR  ]");
	}
	
	
	public JobLogger(OptionsLogger options, Map<String, String> dbParamsMap) {
		this.options = options;
		this.dbParams = dbParamsMap;
	}

	public void init() throws MyLogException {
		this.logger = Logger.getLogger("MyLog");
		this.logger.setUseParentHandlers(false);

		if (!this.options.isOptionLogError() && !this.options.isOptionLogMessage()
				&& !this.options.isOptionLogWarning()) {
			throw new MyLogException("Error or Warning or Message must be specified");
		}

		if (!this.options.isOptionLogToConsole() && !this.options.isOptionLogToFile()
				&& !this.options.isOptionLogToDatabase()) {
			throw new MyLogException("Invalid configuration, you must log somewhere");
		}

		SimpleFormatter simpleFormatter = new SimpleFormatter() {
			private static final String FORMAT = "%1$s %n";

			@Override
			public synchronized String format(LogRecord lr) {
				return String.format(FORMAT, lr.getMessage());
			}
		};

		if (this.options.isOptionLogToConsole()) {
			this.ch = new ConsoleHandler();
			this.ch.setFormatter(simpleFormatter);
		}

		if (this.options.isOptionLogToFile()) {
			try {
				File logFile = new File(dbParams.get("logFile"));
				if (!logFile.exists()) {
					logFile.createNewFile();
				}
				this.fh = new FileHandler(dbParams.get("logFile"));
				this.fh.setFormatter(simpleFormatter);
			} catch (Exception e) {
				throw new MyLogException("Error setting file to write logs");
			}
		}
	}

	public void logMessage(String messageText, TypesLogs typeLog) throws MyLogException {

		if (validateInput(messageText, typeLog).equals(VALIDATE_NOT_LOG))
			return;

		writeLogs(formatMessage(messageText, typeLog));
	}

	private Integer validateInput(String messageText, TypesLogs typeLog) throws MyLogException {

		if ((TypesLogs.TYPE_MESSAGE.equals(typeLog) && !this.options.isOptionLogMessage())
				|| (TypesLogs.TYPE_WARNING.equals(typeLog) && !this.options.isOptionLogWarning())
				|| (TypesLogs.TYPE_ERROR.equals(typeLog) && !this.options.isOptionLogError())) {
			return VALIDATE_NOT_LOG;
		}

		if (messageText == null || messageText.trim().isEmpty()) {
			return VALIDATE_NOT_LOG;
		}

		if (DESCRIPTION_TYPES.get(typeLog) == null) {
			throw new MyLogException("Error or Warning or Message must be specified");
		}

		return VALIDATE_OK;
	}

	private static String formatMessage(String messageText, TypesLogs typeLog) {
		messageText = messageText.trim();

		StringBuilder messageFormatted = new StringBuilder();
		messageFormatted.append(DESCRIPTION_TYPES.get(typeLog)).append(" - ")
				.append(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG).format(new Date()))
				.append(" - ").append(messageText);

		return messageFormatted.toString();
	}

	private void writeLogs(String messageText) {

		InvokerLoggerTasks invoker = new InvokerLoggerTasks();

		if (this.options.isOptionLogToConsole()) {
			LoggerConsoleTask loggerConsole = new LoggerConsoleTask(messageText, this.logger, this.ch);
			invoker.addTask(loggerConsole);
		}

		if (this.options.isOptionLogToFile()) {
			LoggerFileTask loggerFile = new LoggerFileTask(messageText, this.logger, this.fh);
			invoker.addTask(loggerFile);
		}

		if (this.options.isOptionLogToDatabase()) {
			LoggerDbTask loggerDb = new LoggerDbTask(messageText, this.dbParams);
			invoker.addTask(loggerDb);
		}

		invoker.runAllTasks();
	}
}