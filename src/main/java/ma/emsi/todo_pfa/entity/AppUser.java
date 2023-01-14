package ma.emsi.todo_pfa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class AppUser {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String username;
	private String password;
	@ManyToMany
	@JoinTable(
			  name = "user_task", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "task_id"))
	private List<Task> tasks;
	
	
	public void addTask(Task newTask) {
		if (tasks == null)
			tasks = new ArrayList<Task>();

		tasks.add(newTask);
	}
}
