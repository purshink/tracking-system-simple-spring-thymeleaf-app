package com.example.tracking_system.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City extends BaseEntity{

    private CityNameEnum name;
    private int postcode;

    public City() {
    }
    @Column
    @Enumerated
    public CityNameEnum getName() {
        return name;
    }

    public void setName(CityNameEnum name) {
        this.name = name;
    }


    @Column
    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
}
