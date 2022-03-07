package com.boa.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boa.userservice.models.BOAUser;
import com.boa.userservice.services.UserService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
	private UserService userService;
	
    private Gson gson;
    //add
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
    
    
}
