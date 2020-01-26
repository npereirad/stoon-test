package com.natanael.stoon.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanael.stoon.api.entities.Address;
import com.natanael.stoon.api.repositories.AddressRepository;
import com.natanael.stoon.api.services.AddressService;


@Service
public class AddressServiceImpl implements AddressService {

	private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public Optional<Address> findById(Long id) {
		log.info("Finding address by Id {}",id);
		return this.addressRepository.findById(id);
	}

	@Override
	public List<Address> findAll() {
		log.info("Finding all addresses");
		return this.addressRepository.findAll();
	}

	@Override
	public Address persit(Address address) {
		log.info("Persisting address: {}", address);
		return this.addressRepository.save(address);
	}

	@Override
	public void delete(Address address) {
		log.info("Deleting address: {}", address);
		this.addressRepository.delete(address);
		
	}

}
