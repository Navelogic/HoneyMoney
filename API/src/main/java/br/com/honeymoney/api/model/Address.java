package br.com.honeymoney.api.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Embeddable
public class Address {

    // String
    private String surName;
    private String type;
    private String publicPlace;
    private String number;
    private String complement;
    private String neighborhood;
    private String zipCode;

    private String cityName;
    @Size(max = 2, message = "Estado deve ter 2 caracteres")
    private String stateTag;
    private String countryName;

    // Getters and Setters
    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String cep) {
        this.zipCode = cep;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String city) {
        this.cityName = city;
    }

    public String getStateTag() {
        return stateTag;
    }

    public void setStateTag(String state) {
        this.stateTag = state;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String country) {
        this.countryName = country;
    }

}
