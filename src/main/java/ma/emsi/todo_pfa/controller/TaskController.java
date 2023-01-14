package ma.emsi.todo_pfa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.todo_pfa.entity.AppUser;
import ma.emsi.todo_pfa.entity.Task;
import ma.emsi.todo_pfa.model.TasksListModel;
import ma.emsi.todo_pfa.service.TaskService;
import ma.emsi.todo_pfa.service.UserService;

@RestController() @RequestMapping("/task") @CrossOrigin(origins = "*")
public class TaskController {
	
	@Autowired
	TaskService taskSer;
	@Autowired
	UserService userSer;
	
	public AppUser getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		return userSer.getUser(username);
		
	}	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/personal")
	public List<Task> personalTask() {
		return taskSer.getUserTasks(getCurrentUser().getUserId());
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/personal")
	public ResponseEntity<Object> addTask(@RequestParam(name ="name") String name, @RequestParam(name="deadline") Date deadline) {
		
		if(taskSer.add(new Task(0, name, deadline, false, "crée", new Date()), getCurrentUser().getUserId()))
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
			
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/other_user")
	public ResponseEntity<Object> addTask(@RequestParam(name ="name") String name, @RequestParam(name="deadline") Date deadline, @RequestParam(name="user_id") int user_id) {
		
		if(taskSer.add(new Task(0, name, deadline, false, "crée par " + getCurrentUser().getUsername(), new Date()), user_id))
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
	
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/done")
	public ResponseEntity<Object> isTaskDone(@RequestParam(name ="task_id") int task_id, @RequestParam(name ="done") boolean done){
		
		for(Task task: getCurrentUser().getTasks()) {
			if(task.getTaskId() == task_id) {
				if(taskSer.isDone(task_id, done, getCurrentUser().getUsername()))
					return new ResponseEntity<>(HttpStatus.ACCEPTED);
				else
					return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/all")
	public TasksListModel getUndoneTasks() {
		return taskSer.getUndoneTasks(getCurrentUser().getUserId());
	}
	
}
