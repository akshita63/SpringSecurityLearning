package com.demoExample.CustomService;

import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demoExample.Entity.UsersEntity;
import com.demoExample.Exceptions.UserNotFound;
import com.demoExample.Repos.UserRepositoryy;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	
	private final UserRepositoryy userrepo ;
	
	public  CustomUserDetailsService(UserRepositoryy userrepo) {
		this.userrepo=userrepo;
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UsersEntity user= userrepo.findByUsername(username);
		
		//check the usernames with my already registered usernames
//		if(Objects.isNull(user)) {
			//throw some exception
			
	//	}
		if(user==null) {
			//throw some exception here
			System.out.println("user not found");
			//throw new exception
			throw new UsernameNotFoundException("user not found");
		}
		
		else {
			//add the data of user like my username and password in my customuserdetails class
			return new  CustomUserDetails (user);
		}
		
	}

}
