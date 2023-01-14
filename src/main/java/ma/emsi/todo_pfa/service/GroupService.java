package ma.emsi.todo_pfa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ma.emsi.todo_pfa.entity.Group;
import ma.emsi.todo_pfa.entity.Task;


public interface GroupService {

	boolean create(String name, int adminId, List<Integer> usersIds);

	List<Group> getUserGroupes(int userId);

	Task affectTaskToGroup(int taskId, int groupId, int userId);
}
