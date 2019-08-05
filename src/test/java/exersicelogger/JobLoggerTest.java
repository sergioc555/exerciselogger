package exersicelogger;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import exerciselogger.JobLogger;
import exerciselogger.JobLogger.TypesLogs;
import exerciselogger.model.OptionsLogger;

public class JobLoggerTest {

	private static Map<String, String> dbParamsMap;

	@BeforeClass
	public static void executeBeforeAll() {

		Map<String, String> param = new HashMap<String, String>();

		param.put("userName", "usuario");
		param.put("password", "123456");
		param.put("url", "jdbc:sqlite:C:\\BaseDatos\\sqllite\\util.db");
		param.put("logFile", "C:/proyectos/logFile.txt");

		dbParamsMap = param;
	}

	@Test
	public void loggerTextFile() {
		String resultado;
		String message = "JUST IN FILE";
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			optionsLogger.setOptionLogToDatabase(false);
			optionsLogger.setOptionLogToConsole(false);
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			jobLogger.logMessage(message, TypesLogs.TYPE_MESSAGE);
			jobLogger.logMessage(message, TypesLogs.TYPE_ERROR);
			jobLogger.logMessage(message, TypesLogs.TYPE_WARNING);
			resultado = "OK";
		} catch (Exception e) {
			e.printStackTrace();
			resultado = "NO OK";
		}
		Assert.assertEquals("OK", resultado);
	}

	@Test
	public void loggerTextConsole() {
		String resultado;
		String message = "JUST IN CONSOLE";
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			optionsLogger.setOptionLogToDatabase(false);
			optionsLogger.setOptionLogToFile(false);
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			jobLogger.logMessage(message, TypesLogs.TYPE_MESSAGE);
			jobLogger.logMessage(message, TypesLogs.TYPE_ERROR);
			jobLogger.logMessage(message, TypesLogs.TYPE_WARNING);
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("OK", resultado);
	}

	@Test
	public void loggerTextDatabase() {
		String resultado;
		String message = "JUST IN DB";
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			optionsLogger.setOptionLogToConsole(false);
			optionsLogger.setOptionLogToFile(false);
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			jobLogger.logMessage(message, TypesLogs.TYPE_MESSAGE);
			jobLogger.logMessage(message, TypesLogs.TYPE_ERROR);
			jobLogger.logMessage(message, TypesLogs.TYPE_WARNING);
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("OK", resultado);
	}

	@Test
	public void loggerWhenDatabaseConexionError() {
		String resultado;
		String message = "JUST IN DB NO CONECCT";

		Map<String, String> param = new HashMap<String, String>();

		param.put("userName", "usuario");
		param.put("password", "123456");
		param.put("url", "jdbc:sqlite:C:\\BaseDatos\\sqllite\\utaaail.db");
		param.put("logFile", "C:/proyectos/logFile.txt");

		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			optionsLogger.setOptionLogToConsole(false);
			optionsLogger.setOptionLogToDatabase(true);
			optionsLogger.setOptionLogToFile(false);
			JobLogger jobLogger = new JobLogger(optionsLogger, param);
			jobLogger.init();
			jobLogger.logMessage(message, TypesLogs.TYPE_MESSAGE);
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("OK", resultado);
	}

	@Test
	public void loggerMessage() {

		String resultado;
		String message = "ALL /MSG";
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			jobLogger.logMessage(message, TypesLogs.TYPE_MESSAGE);
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("OK", resultado);
	}

	@Test
	public void loggerWarning() {
		String resultado;
		String message = "ALL /WRN";
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			jobLogger.logMessage(message, TypesLogs.TYPE_WARNING);
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("OK", resultado);
	}

	@Test
	public void loggerError() {
		String resultado;
		String message = "ALL /ERR";
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			jobLogger.logMessage(message, TypesLogs.TYPE_ERROR);
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("OK", resultado);
	}

	@Test
	public void loggerWhenEmpty() {
		String resultado;
		String message = "  ";
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			jobLogger.logMessage(message, TypesLogs.TYPE_ERROR);
			jobLogger.logMessage(null, TypesLogs.TYPE_ERROR);
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("OK", resultado);
	}

	@Test
	public void loggerWhenOptionsIsJustMessage() {
		String resultado;
		String message = "LOG JUST MESSAGES";
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			optionsLogger.setOptionLogMessage(true);
			optionsLogger.setOptionLogWarning(false);
			optionsLogger.setOptionLogError(false);
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			jobLogger.logMessage(message, TypesLogs.TYPE_MESSAGE);
			jobLogger.logMessage(message, TypesLogs.TYPE_ERROR);
			jobLogger.logMessage(message, TypesLogs.TYPE_WARNING);
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("OK", resultado);
	}


	@Test
	public void loggerWhenOptionsIsJustWarning() {
		String resultado;
		String message = "LOG JUST WARNING";
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			optionsLogger.setOptionLogMessage(false);
			optionsLogger.setOptionLogWarning(true);
			optionsLogger.setOptionLogError(false);
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			jobLogger.logMessage(message, TypesLogs.TYPE_MESSAGE);
			jobLogger.logMessage(message, TypesLogs.TYPE_ERROR);
			jobLogger.logMessage(message, TypesLogs.TYPE_WARNING);
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("OK", resultado);
	}
	
	@Test
	public void loggerWhenOptionsIsJustError() {
		String resultado;
		String message = "LOG JUST ERROR";
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			optionsLogger.setOptionLogMessage(false);
			optionsLogger.setOptionLogWarning(false);
			optionsLogger.setOptionLogError(true);
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			jobLogger.logMessage(message, TypesLogs.TYPE_MESSAGE);
			jobLogger.logMessage(message, TypesLogs.TYPE_ERROR);
			jobLogger.logMessage(message, TypesLogs.TYPE_WARNING);
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("OK", resultado);
	}

	@Test
	public void loggerWhenOptionsIsNotSetLevel() {
		String resultado;
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			optionsLogger.setOptionLogMessage(false);
			optionsLogger.setOptionLogWarning(false);
			optionsLogger.setOptionLogError(false);
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("NO OK", resultado);
	}

	@Test
	public void loggerWhenOptionsIsNotSetPlaceToLog() {
		String resultado;
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			optionsLogger.setOptionLogToConsole(false);
			optionsLogger.setOptionLogToDatabase(false);
			optionsLogger.setOptionLogToFile(false);
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("NO OK", resultado);
	}

	@Test
	public void loggerWhenTypeLogIsNUllThenThrowsException() {
		String resultado;
		String message = "ABC";
		try {
			OptionsLogger optionsLogger = new OptionsLogger();
			JobLogger jobLogger = new JobLogger(optionsLogger, dbParamsMap);
			jobLogger.init();
			jobLogger.logMessage(message, null);
			resultado = "OK";
		} catch (Exception e) {
			resultado = "NO OK";
		}
		Assert.assertEquals("NO OK", resultado);
	}

}