package com.natanael.stoon.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natanael.stoon.api.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
}
