package ma.emsi.todo_pfa.model;

import java.util.ArrayList;
import java.util.List;

import ma.emsi.todo_pfa.entity.Task;

public class TasksListModel {

	private List<TaskModel> personalTasks;
	private List<TaskModel> sharedTasks;
	private List<TaskModel> groupTasks;
	
	
	public void addGroupTask(Task t, String name) {
		if(groupTasks == null)
			groupTasks = new ArrayList<TaskModel>();
	
		groupTasks.add(new TaskModel(t, "groupe : "+ name));
	}
	
	public void addSharedTasks(Task t, String name) {
		if(sharedTasks == null)
			sharedTasks = new ArrayList<TaskModel>();
	
		sharedTasks.add(new TaskModel(t, "Utilisateur : " + name));
	}
	
	public void addPersonalTasks(Task t) {
		if(personalTasks == null)
			personalTasks = new ArrayList<TaskModel>();
	
		personalTasks.add(new TaskModel(t));
	}

	public List<TaskModel> getPersonalTasks() {
		return personalTasks;
	}

	public void setPersonalTasks(List<TaskModel> personalTasks) {
		this.personalTasks = personalTasks;
	}

	public List<TaskModel> getSharedTasks() {
		return sharedTasks;
	}

	public void setSharedTasks(List<TaskModel> sharedTasks) {
		this.sharedTasks = sharedTasks;
	}

	public List<TaskModel> getGroupTasks() {
		return groupTasks;
	}

	public void setGroupTasks(List<TaskModel> groupTasks) {
		this.groupTasks = groupTasks;
	}
	
	
}
