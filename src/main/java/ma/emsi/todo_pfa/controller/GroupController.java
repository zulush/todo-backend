package ma.emsi.todo_pfa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.todo_pfa.entity.AppUser;
import ma.emsi.todo_pfa.entity.Group;
import ma.emsi.todo_pfa.entity.Task;
import ma.emsi.todo_pfa.model.GroupModel;
import ma.emsi.todo_pfa.service.GroupService;
import ma.emsi.todo_pfa.service.UserService;

@RestController() @RequestMapping("/group") @CrossOrigin(origins = "*")
public class GroupController {

	@Autowired
	GroupService groupSer;
	@Autowired
	UserService userSer;
	
	public AppUser getCurrentUser() {
		return userSer.getUser(1);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("")
	public ResponseEntity<Object> addGroup(@RequestParam(name ="name") String name, @RequestParam(name ="user_id") List<Integer> usersIds){
	
		if(groupSer.create(name, getCurrentUser().getUserId(), usersIds))
			return new ResponseEntity<>(HttpStatus.OK);
	
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("")
	public ResponseEntity<List<GroupModel>> getMyGroups(){
		
		List<GroupModel> groups = new ArrayList<GroupModel>();
		
		for(Group g: groupSer.getUserGroupes(getCurrentUser().getUserId())) {
			groups.add(new GroupModel(g));
		}
		System.out.println(groups);
		
		if(!groups.isEmpty())
			return ResponseEntity.ok(groups);
	
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/task")
	public ResponseEntity<Task> AffectTaskToGroups(@RequestParam(name ="task_id") int taskId, @RequestParam(name ="group_id") int groupId){
		
		Task task = groupSer.affectTaskToGroup(taskId, groupId, this.getCurrentUser().getUserId());
		
		if(task != null)
			return ResponseEntity.ok(task);
	
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
