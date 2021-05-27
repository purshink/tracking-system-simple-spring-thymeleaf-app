package com.example.tracking_system.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client extends BaseEntity {
    private City city;
    private Country country;
    private String address;
    private String contactPerson;
    private String phoneNumber;
    private String bankName;
    private String bic;
    private String iban;
    private String uid;
    private String vat;
    private String code;

    public Client() {
    }

    @ManyToOne
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    @ManyToOne
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name = "contact_person")
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Column(name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    @Column(name = "bic")
    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }
    @Column(name = "iban",unique = true)
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Column
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    @Column
    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }
    @Column
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
