package com.example.tracking_system.view;

import com.example.tracking_system.model.entity.CityNameEnum;
import com.example.tracking_system.model.entity.CountryNameEnum;

public class SupplierViewModel {
    private Long id;
    private String supplierName;
    private CityNameEnum city;
    private CountryNameEnum country;
    private String address;
    private String contactPerson;
    private String phoneNumber;
    private String bankName;
    private String bic;
    private String iban;
    private String uid;
    private String vat;
    private String code;

    public SupplierViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public CityNameEnum getCity() {
        return city;
    }

    public void setCity(CityNameEnum city) {
        this.city = city;
    }

    public CountryNameEnum getCountry() {
        return country;
    }

    public void setCountry(CountryNameEnum country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
