package ma.emsi.todo_pfa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.todo_pfa.entity.AppUser;
import ma.emsi.todo_pfa.entity.Task;
import ma.emsi.todo_pfa.model.UserModel;
import ma.emsi.todo_pfa.repository.TaskRepository;
import ma.emsi.todo_pfa.repository.UserRepository;

@Service  @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService,UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	TaskRepository taskRepo;
	
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepo.getByUsername(username);	
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		return new User(user.getUsername(), user.getPassword(), authorities);
	}
	@Override
	public UserModel getByUsername(String username) {
		AppUser user = userRepo.getByUsername(username);
		
		if (user != null) {
			UserModel userModel = new UserModel(user.getUserId(), user.getUsername());
			return userModel;
		}
		
		return null;
	}

	@Override
	public boolean addTaskToUser(int task_id, int user_id) {
		
		if(userRepo.findById(user_id).isEmpty() || taskRepo.findById(task_id).isEmpty())
			return false;
		
		AppUser user = userRepo.findById(user_id).get();
		Task task = taskRepo.findById(task_id).get();
		
		if(user.getTasks().contains(task))
			return false;
		
		user.addTask(task);
		
		userRepo.save(user);
		
		return true;
	}

	@Override
	public AppUser getUser(int user_id) {
		return userRepo.findById(user_id).get();
	}

	@Override
	public List<AppUser> getUsers() {
		log.info("get  All User on the data base");
		return userRepo.findAll();
	}

	@Override
	public AppUser addUser(AppUser u) {
		log.info("saving new User {} to the data base"+ u.getUsername());
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return userRepo.save(u);
		
	}
	@Override
	public AppUser getUser(String username) {
	
		return userRepo.findByUsername(username);
	}



}
