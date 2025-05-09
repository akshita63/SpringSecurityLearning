package com.demoExample.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.demoExample.CustomService.CustomUserDetails;

//this class is basically used for configuration
//if you want to modify the authentication i i should implement my userservicedetails and also password encoder and also dao
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userdetails;
	
	//define a security filter chain for customising, here I have bispassed all requests directly to the controller
	
	@Bean
	//HttpSecurity is used to define the security rules like authentication, authorosation, csrf etc
	//SecurityFilterchain is an interface of filters
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		
		//as of now did not give any authentication to any request
		security.
		  authorizeHttpRequests(req -> req
		            .antMatchers("/register","/login").permitAll() // Allow /register endpoint for everyone
		            .anyRequest().authenticated() // Require authentication for all other requests
		        )
		.httpBasic(Customizer.withDefaults())//this is to tell to give basic default authentication
	//	.formLogin(Customizer.withDefaults())//basic form authentication
		.csrf().disable();
		//this uses my custome form login
				
		//build the security filter chain based on the configuration done
		return security.build();
		
		
	}
	
	//I need object of UserDetailsService
	//configuration for my userdetails service
//	@Bean
//	public UserDetailsService userDetails() {
//		//Userdetails is an interface which implements UserDetailsService and User is the class which implements my UserDetails
//		
//		//this is a limitation because i am just using the same objects which I am giving in memory not from the db
//		UserDetails akshita=User.withUsername("akshita")
//				//plain text password
//				//not suggested to use in production
//				.password("{noop}password")
//				.roles("user")
//				.build();
//		
//		
//		UserDetails sai=User.withUsername("SAI")
//			.password("{noop}SAI")
//				.roles("user")
//				.build();
		
		
		//return the object of UserDetailsService
		//we should give the name of object which implements my 
	//	return new InMemoryUserDetailsManager( akshita, sai);
		//return new JdbcDaoImpl();	
		
	//}
	
	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	//I need to configure my authenticationprovider bean
	//I created my customuserdetails and also authenticationprovider which authenticates against my db
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider dao=new  DaoAuthenticationProvider();
		dao.setUserDetailsService(userdetails);
	//	dao.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		//new BCryptPasswordEncoder(
		dao.setPasswordEncoder(new BCryptPasswordEncoder(11) );
		return dao;
		
		
	}
	
	

}
