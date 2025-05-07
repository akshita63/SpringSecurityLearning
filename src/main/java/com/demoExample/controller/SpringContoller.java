package com.demoExample.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demoExample.Entity.UsersEntity;
import com.demoExample.Repos.UserRepositoryy;




@RestController
public class SpringContoller {
	
	
	@Autowired
	private  UserRepositoryy userrepo;
	
	//created a record
	public record Student(String studentName, int id) {
	    // Constructor, getters, and toString() are automatically generated
	}
	
	List<Student> stu=new ArrayList<>(List.of(new Student("akshita", 12),
			new Student("ananya", 19)));
	
	
	@GetMapping("/studlist")
	public List<Student> getStudents() {
		return stu;
	}
	
	
	
		
	
	@PostMapping("/post")
	public  Student saveStudent(@RequestBody Student stud) {
		stu.add(stud);
		return stud;
	}
	
	
	@GetMapping("/springsec")
	public String hello() {
		return "hello";
		
	}
	
	@GetMapping("/spring")
	public String hi() {
		return "hi";
	}
	
	@GetMapping("/csrf")
	public CsrfToken GetToken(HttpServletRequest req) {
		return (CsrfToken) req.getAttribute("_csrf");//here i need to get the value of csrf token which I have passed in my request,so basically token is part of my request
	}
	
	@PostMapping("/register")
	public UsersEntity saveusers(@RequestBody UsersEntity users ) {
		UsersEntity us=userrepo.save(users);
		return us;
		
	}
	
	//create a login page and check if the registered user is authenticated
	@PostMapping("/login")
	public String LogUsesrs(@RequestBody UsersEntity users ) {
		UsersEntity us= userrepo.findByUsername(users.getUsername());
		//this is even better for checking
		if(us!=null) {
			
			if(us.getUsername().equals(users.getUsername()) && us.getPassword().equals(users.getPassword())  ){
		
			return "success";
			
		}
		else {
			return "failure";
		}
		}
		else {
				return "user not found";
			}
		
		
		}
	}

