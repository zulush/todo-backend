package ma.emsi.todo_pfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.todo_pfa.entity.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer>{

	public AppUser getByUsername(String username);
	public AppUser findByUsername(String Username);
}
