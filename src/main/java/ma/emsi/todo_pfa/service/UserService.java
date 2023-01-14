package ma.emsi.todo_pfa.service;

import java.util.List;

import ma.emsi.todo_pfa.entity.AppUser;
import ma.emsi.todo_pfa.model.UserModel;

public interface UserService {

	UserModel getByUsername(String username);

	boolean addTaskToUser(int task_id, int user_id);

	AppUser getUser(int i);
	AppUser getUser(String username);
	List<AppUser> getUsers();
	
	AppUser addUser(AppUser u);

}
