package com.boa.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boa.userservice.models.BOAUser;

public interface UserRepo extends JpaRepository<BOAUser,Long>{

}
