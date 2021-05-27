package com.example.tracking_system.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity{
    private CountryNameEnum name;
    private String countryCode;

    public Country() {
    }

    @Column
    @Enumerated
    public CountryNameEnum getName() {
        return name;
    }

    public void setName(CountryNameEnum name) {
        this.name = name;
    }



    @Column(name = "country_code")
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
