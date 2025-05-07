package com.demoExample.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoExample.Entity.UsersEntity;
import java.util.List;



@Repository
public interface UserRepositoryy extends JpaRepository<UsersEntity, Integer> {
	
	//custom method for my username checking
	
	UsersEntity  findByUsername(String username);

}
