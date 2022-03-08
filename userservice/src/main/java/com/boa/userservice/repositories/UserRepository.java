package com.boa.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boa.userservice.models.User;




public interface UserRepository extends JpaRepository<User,String>{

}
