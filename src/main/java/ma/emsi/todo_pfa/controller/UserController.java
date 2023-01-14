package ma.emsi.todo_pfa.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import ma.emsi.todo_pfa.entity.AppUser;
import ma.emsi.todo_pfa.entity.Task;
import ma.emsi.todo_pfa.model.UserModel;
import ma.emsi.todo_pfa.service.UserService;


@RestController() @RequestMapping("/user") @CrossOrigin(origins = "*") @RequiredArgsConstructor 
public class UserController {

	@Autowired
	UserService userSer;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/search")
	public ResponseEntity<UserModel> getByUsername(@RequestParam(name ="username") String username) {
		
		UserModel user = userSer.getByUsername(username);
		if(user != null)
			return ResponseEntity.ok(user);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/add_task")
	public ResponseEntity<Object> addTaskToUser(@RequestParam(name ="task_id") int task_id, @RequestParam(name ="user_id") int user_id){
		
		if(userSer.addTaskToUser(task_id, user_id))
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@GetMapping("/users")
	public ResponseEntity<List<AppUser>>getUsers(){
		return ResponseEntity.ok().body(userSer.getUsers());
	}
	@GetMapping("/{userId}")
	public ResponseEntity<AppUser>getUser(@PathVariable int userId){
		return ResponseEntity.ok().body(userSer.getUser(userId));
	}
	
	@PostMapping("/save")
	public ResponseEntity<AppUser>saveUser(@RequestParam String username , @RequestParam String password){
		List<Task>t = new ArrayList<>();
	AppUser u =new AppUser(0,username,password,t);
		return ResponseEntity.ok().body(userSer.addUser(u));
	}
	
	
	
}
