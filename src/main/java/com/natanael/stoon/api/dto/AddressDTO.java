package com.natanael.stoon.api.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class AddressDTO {


	private Long id;
	private String streetName;
	//precisa ser String, pois exite registros com S/N ou sem numero
	private String number;
	private String complement;
	private String neighbourhood;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private double latitude;
	private double longitude;
	
	public AddressDTO() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message="Nome da Rua não pode ser vazio.")
	@Length(min=5, max=255, message="Nome da Rua deve conter entre 5 e 255 caracteres")
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	@NotEmpty(message="Número não pode ser vazio.")
	@Length(min=1, max=10, message="Número deve conter entre 1 e 10 caracteres")
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(max=255, message="Complemento deve conter no máximo 255 caracteres")
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	@NotEmpty(message="Bairro não pode ser vazio.")
	@Length(min=1, max=255, message="Bairro deve conter entre 1 e 255 caracteres")
	public String getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	
	@NotEmpty(message="Cidade não pode ser vazio.")
	@Length(min=1, max=255, message="Cidade deve conter entre 1 e 255 caracteres")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@NotEmpty(message="UF não pode ser vazio.")
	@Length(min=2, max=2, message="UF deve conter 2 caracteres")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@NotEmpty(message="Pais não pode ser vazio.")
	@Length(min=1, max=255, message="Pais deve conter entre 1 e 255 caracteres")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@NotEmpty(message="CEP não pode ser vazio.")
	@Length(max=10, message="CEP deve conter no máximo 10 caracteres")
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
