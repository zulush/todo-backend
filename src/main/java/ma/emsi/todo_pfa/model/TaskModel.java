package ma.emsi.todo_pfa.model;

import java.util.Date;

import ma.emsi.todo_pfa.entity.Task;


public class TaskModel {

	private int id;
	private String name;
	private Date deadline;
	private String sharedWith = ""; 
	
	
	TaskModel(Task task){
		this.id = task.getTaskId();
		this.name = task.getName();
		this.deadline = task.getDeadline();
	}
	
	TaskModel(Task task,String sharedWith){
		this.id = task.getTaskId();
		this.name = task.getName();
		this.deadline = task.getDeadline();
		this.sharedWith = sharedWith;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getSharedWith() {
		return sharedWith;
	}

	public void setSharedWith(String sharedWith) {
		this.sharedWith = sharedWith;
	}
	
	
}
