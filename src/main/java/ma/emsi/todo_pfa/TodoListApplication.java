package ma.emsi.todo_pfa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.emsi.todo_pfa.entity.AppUser;
import ma.emsi.todo_pfa.entity.Task;
import ma.emsi.todo_pfa.service.UserService;


@SpringBootApplication
public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
		
	}
	/*@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			List<Task>t = new ArrayList<>();
			userService.addUser(new AppUser(0,"zouizza","zouizza",t));
		
			
		};
	}*/
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	


}
