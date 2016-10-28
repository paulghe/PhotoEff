package messaging;

import java.util.Random;

import types.TaskType;

public abstract class Message {
	private TaskType taskType;
	private int messageId;
	
	public Message(TaskType taskType) {
		super();
		this.taskType = taskType;
		if (taskType!=null)
			generateId();
	}
	
	public void generateId() {
		Random r = new Random();
		this.messageId = r.nextInt(10000000);
	}
	
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public int getId() {
		return messageId;
	}
	
	
}
