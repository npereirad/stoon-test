package com.natanael.stoon.api.controlles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natanael.stoon.api.dto.AddressDTO;
import com.natanael.stoon.api.entities.Address;
import com.natanael.stoon.api.response.Response;
import com.natanael.stoon.api.services.AddressService;

@RestController
@RequestMapping("api/address")
@CrossOrigin(origins = "*")
public class AddressController {

	private static final Logger log = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	private AddressService addressService;
	
	public AddressController() {
		
	}
	
	@PostMapping
	public ResponseEntity<Response<AddressDTO>> create(@Valid @RequestBody AddressDTO addressDto, BindingResult result){
		log.info("Creating Address: {}", addressDto.toString());
		Response<AddressDTO> response = new Response<AddressDTO>();

		Address address = this.convertDtoToAddress(addressDto);

		if (result.hasErrors()) {
			log.error("Errors in validation: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		address = this.addressService.persit(address);
		
		response.setData(this.convertAddressDto(address));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<Response<AddressDTO>> findById(@PathVariable("id") Long id){
		log.info("Looking for address by Id: {}", id);
		Response<AddressDTO> response = new Response<AddressDTO>();
		Optional<Address> address = addressService.findById(id);

		if (!address.isPresent()) {
			log.info("Address not found by Id: {}", id);
			response.getErrors().add("Endereço não encontrado para o Id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.convertAddressDto(address.get()));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value="/")
	public ResponseEntity<Response<List<AddressDTO>>> findAll(){
		log.info("Listing all addresses");
		Response<List<AddressDTO>> response = new Response<List<AddressDTO>>();
		List<AddressDTO> dtos = new ArrayList<AddressDTO>();
		this.addressService.findAll().forEach(address -> dtos.add(convertAddressDto(address)));
		response.setData(dtos);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<AddressDTO>> update(@Valid @RequestBody AddressDTO addressDto, BindingResult result){
		log.info("Updating Address: {}", addressDto.toString());
		Response<AddressDTO> response = new Response<AddressDTO>();

		Optional<Address> address = this.addressService.findById(addressDto.getId());
		if (!address.isPresent()) {
			log.error("Address not found for Id: {}",addressDto.getId());
			response.getErrors().add("Endereço não encontrado para o Id "+addressDto.getId());
			return ResponseEntity.badRequest().body(response);
		}
		
		if (result.hasErrors()) {
			log.error("Errors in validation: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Address addressReturn = address.get();
		addressReturn.setStreetName(addressDto.getStreetName());
		addressReturn.setNumber(addressDto.getNumber());
		addressReturn.setComplement(addressDto.getComplement());
		addressReturn.setNeighbourhood(addressDto.getNeighbourhood());
		addressReturn.setCity(addressDto.getCity());
		addressReturn.setState(addressDto.getState());
		addressReturn.setCountry(addressDto.getCountry());
		addressReturn.setZipcode(addressDto.getZipcode());
		addressReturn.setLatitude(addressDto.getLatitude());
		addressReturn.setLongitude(addressDto.getLongitude());
				
		addressReturn = this.addressService.persit(addressReturn);
		
		response.setData(this.convertAddressDto(addressReturn));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response<AddressDTO>>  delete(@PathVariable("id") Long id) {
		log.info("Deleting Address by Id: {}", id);
		Response<AddressDTO> response = new Response<AddressDTO>();

		Optional<Address> address = this.addressService.findById(id);
		if (!address.isPresent()) {
			log.error("Address not found for Id: {}",id);
			response.getErrors().add("Endereço não encontrado para o Id "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.addressService.delete(address.get());
		
		response.setData(this.convertAddressDto(address.get()));
		return ResponseEntity.ok(response);		
	}
	
	private AddressDTO convertAddressDto(Address address) {
		AddressDTO dto = new AddressDTO();
		dto.setId(address.getId());
		dto.setStreetName(address.getStreetName());
		dto.setNumber(address.getNumber());
		dto.setComplement(address.getComplement());
		dto.setNeighbourhood(address.getNeighbourhood());
		dto.setCity(address.getCity());
		dto.setState(address.getState());
		dto.setCountry(address.getCountry());
		dto.setZipcode(address.getZipcode());
		dto.setLatitude(address.getLatitude());
		dto.setLongitude(address.getLongitude());
		return dto;
	}

	private Address convertDtoToAddress(AddressDTO addressDto) {
		Address address = new Address();
		address.setId(addressDto.getId());
		address.setStreetName(addressDto.getStreetName());
		address.setNumber(addressDto.getNumber());
		address.setComplement(addressDto.getComplement());
		address.setNeighbourhood(addressDto.getNeighbourhood());
		address.setCity(addressDto.getCity());
		address.setState(addressDto.getState());
		address.setCountry(addressDto.getCountry());
		address.setZipcode(addressDto.getZipcode());
		address.setLatitude(addressDto.getLatitude());
		address.setLongitude(addressDto.getLongitude());
		return address;
	}
}
