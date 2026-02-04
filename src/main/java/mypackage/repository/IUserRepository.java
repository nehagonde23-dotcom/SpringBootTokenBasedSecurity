package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	  	User findByUsername(String username);
	    boolean existsByUsername(String username);
}
