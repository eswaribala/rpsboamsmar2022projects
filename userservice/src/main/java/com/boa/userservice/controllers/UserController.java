package com.boa.userservice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boa.userservice.dtos.JSONBOAUser;
import com.boa.userservice.models.BOAUser;
import com.boa.userservice.services.BOAUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.google.gson.Gson;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
	private BOAUserService userService;
	
    private Gson gson;
    //add
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"/v1.0", "/v1.1"})
    public ResponseEntity<String> addUser(@RequestBody BOAUser boaUser){
    	gson=new Gson();
    	BOAUser userObj=this.userService.addUser(boaUser);
    	if(userObj!=null) {
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(gson.toJson(userObj));
    	}
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Added");
    	
    }
    
    //geatll
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/v1.0", "/v1.1"})
    public List<BOAUser> getAllUsers(){
    	return this.userService.getAllUsers();
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/v1.0/{userId}", "/v1.1/{userId}"})
    public ResponseEntity<String> getUserById(@PathVariable("userId") long userId) {
    	BOAUser userObj=this.userService.getUserById(userId);
    	gson=new Gson();
    	if(userObj!=null) {
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(gson.toJson(userObj));
    	}
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Available");
    	
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping({"/v1.0/{userId}", "/v1.1/{userId}"})
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") long userId) {
    	
    	
    	if(this.userService.deleteUserById(userId)) {
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body("User Deleted");
    	}
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Available");
    	
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/v1.0", "/v1.1"})
    public ResponseEntity<String> updateUser(@RequestBody JSONBOAUser boaUser){
    	gson=new Gson();
    	BOAUser userObj=this.userService.updateUser(boaUser.getUserId(), boaUser.getPassword());
    	if(userObj!=null) {
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(gson.toJson(userObj));
    	}
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not updated");
    	
    }
    
    @GetMapping({"/v1.0/filters/{userId}", "/v1.1/filters/{userId}"})
    public ResponseEntity<String> getUserFilteredDataById(@PathVariable("userId") 
    long userId,@RequestParam(name = "fields", required = false) String fields) {
    	BOAUser userObj=this.userService.getUserById(userId);  
    	
    	
    	if(userObj!=null)
    	{
    		//fields refers to runtime selection
    		ObjectMapper mapper = Squiggly.init(new ObjectMapper(), fields);     		
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(SquigglyUtils.stringify(mapper, userObj));
			

    	}
    	else
    	{
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Available");
    	}

    }
    
}
