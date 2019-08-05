package exerciselogger.model;

public class OptionsLogger {
	
	private boolean optionLogToFile = true;
	private boolean optionLogToConsole = true;
	private boolean optionLogToDatabase = true;
	private boolean optionLogMessage = true;
	private boolean optionLogWarning = true;
	private boolean optionLogError = true;
	
	public boolean isOptionLogToFile() {
		return optionLogToFile;
	}
	public void setOptionLogToFile(boolean optionLogToFile) {
		this.optionLogToFile = optionLogToFile;
	}
	public boolean isOptionLogToConsole() {
		return optionLogToConsole;
	}
	public void setOptionLogToConsole(boolean optionLogToConsole) {
		this.optionLogToConsole = optionLogToConsole;
	}
	public boolean isOptionLogToDatabase() {
		return optionLogToDatabase;
	}
	public void setOptionLogToDatabase(boolean optionLogToDatabase) {
		this.optionLogToDatabase = optionLogToDatabase;
	}
	public boolean isOptionLogMessage() {
		return optionLogMessage;
	}
	public void setOptionLogMessage(boolean optionLogMessage) {
		this.optionLogMessage = optionLogMessage;
	}
	public boolean isOptionLogWarning() {
		return optionLogWarning;
	}
	public void setOptionLogWarning(boolean optionLogWarning) {
		this.optionLogWarning = optionLogWarning;
	}
	public boolean isOptionLogError() {
		return optionLogError;
	}
	public void setOptionLogError(boolean optionLogError) {
		this.optionLogError = optionLogError;
	}
}
