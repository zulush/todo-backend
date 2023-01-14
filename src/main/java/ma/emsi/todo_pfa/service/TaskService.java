package ma.emsi.todo_pfa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ma.emsi.todo_pfa.entity.Task;
import ma.emsi.todo_pfa.model.TasksListModel;

public interface TaskService {

	boolean add(Task task, int userid);

	List<Task> getUserTasks(int userId);

	boolean isDone(int task_id, boolean done, String username);

	TasksListModel getUndoneTasks(int userId);


}
