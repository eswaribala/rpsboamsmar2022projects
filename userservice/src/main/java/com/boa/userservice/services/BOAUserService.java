package com.boa.userservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boa.userservice.models.BOAUser;
import com.boa.userservice.repositories.UserRepo;

@Service
public class BOAUserService {
    @Autowired
	private UserRepo userRepo;
    
    //insert
    
    public BOAUser addUser(BOAUser boaUser) {
    	//db and model
    	return this.userRepo.save(boaUser);
    }
    
    //select all
    
    public List<BOAUser> getAllUsers(){
    	//db and model--select all
    	return this.userRepo.findAll();
    }
    
    //select by id
    
    public BOAUser getUserById(long userId) {
    	
    	//db and model--seelct with where
    	return this.userRepo.findById(userId).orElse(null);
    }
    
    //delete
    
    public boolean deleteUserById(long userId) {
    	boolean status=false;
    	this.userRepo.deleteById(userId);
    	if(this.getUserById(userId)==null)
    		status=true;
    	return status;
    	
    }
    
    //update
    
    public BOAUser updateUser(long userId,String password) {
    	BOAUser boaUser=this.getUserById(userId);
    	if(this.getUserById(userId)!=null) {
    		boaUser.setPassword(password);
    	}
    	return this.userRepo.save(boaUser);
    }
    
    
}
