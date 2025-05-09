package com.demoExample.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demoExample.Entity.UsersEntity;
import com.demoExample.Repos.UserRepositoryy;

@Service
public class UserService {
	
	private UserRepositoryy userrepo;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(PasswordEncoder passwordEncoder ,UserRepositoryy userrepo ) {
		this.passwordEncoder=passwordEncoder;
		this.userrepo=userrepo;
	}

	public UsersEntity register(UsersEntity users) {
		// TODO Auto-generated method stub
		//encrypt the plain password into password encoder and then save the details
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		return userrepo.save(users);
		
		
	}
	
	
	
	

}
