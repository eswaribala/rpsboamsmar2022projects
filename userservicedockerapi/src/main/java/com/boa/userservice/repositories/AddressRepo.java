package com.boa.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boa.userservice.models.Address;

public interface AddressRepo extends JpaRepository<Address,Long> {

}
