package ma.emsi.todo_pfa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.emsi.todo_pfa.entity.Group;

public interface GroupRepository extends JpaRepository<Group,Integer>{

}
