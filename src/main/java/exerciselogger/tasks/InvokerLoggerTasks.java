package exerciselogger.tasks;

import java.util.ArrayList;
import java.util.List;

public class InvokerLoggerTasks {
	private List<ILoggerTask> tasks = new ArrayList<>();
	
	public void addTask(ILoggerTask tarea) {
		this.tasks.add(tarea);
	}
	
	public void runAllTasks() {
		this.tasks.forEach(ILoggerTask::execute);
	}
} 
