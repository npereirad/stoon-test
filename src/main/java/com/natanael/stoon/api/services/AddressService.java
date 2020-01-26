package com.natanael.stoon.api.services;

import java.util.List;
import java.util.Optional;

import com.natanael.stoon.api.entities.Address;

public interface AddressService {
	Optional<Address> findById(Long id);
	
	List<Address> findAll();
	
	Address persit(Address address);
	
	void delete(Address address);
	
}
