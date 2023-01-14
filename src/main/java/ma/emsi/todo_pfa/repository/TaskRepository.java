package ma.emsi.todo_pfa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.emsi.todo_pfa.entity.Task;


public interface TaskRepository extends JpaRepository<Task,Integer>{

	@Query(value = "SELECT t.* FROM task t, user_task ut WHERE t.task_id = ut.task_id and ut.user_id = ?1", nativeQuery = true)
	List<Task> getTasksByUserId(int userId);

	@Query(value = "select u.username from app_user u, user_task ut, task t WHERE t.task_id = ut.task_id and ut.user_id = u.user_id and t.done = false and u.user_id != ?1 and t.task_id = ?2 group by u.username", nativeQuery = true)
	List<String> getUsernameByTask(int userId, int taskId);

}
