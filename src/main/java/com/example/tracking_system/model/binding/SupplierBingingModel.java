package com.example.tracking_system.model.binding;

import javax.validation.constraints.NotBlank;

public class SupplierBingingModel {
    private String supplierName;
    private String city;
    private String country;
    private String address;
    private String contactPerson;
    private String phoneNumber;
    private String bankName;
    private String bic;
    private String iban;
    private String uid;
    private String vat;
    private String code;

    public SupplierBingingModel() {
    }
    @NotBlank(message = "This field can not be blank")
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    @NotBlank(message = "This field can not be blank")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @NotBlank(message = "This field can not be blank")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @NotBlank(message = "This field can not be blank")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @NotBlank(message = "This field can not be blank")
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    @NotBlank(message = "This field can not be blank")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @NotBlank(message = "This field can not be blank")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    @NotBlank(message = "This field can not be blank")
    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }
    @NotBlank(message = "This field can not be blank")
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
    @NotBlank(message = "This field can not be blank")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    @NotBlank(message = "This field can not be blank")
    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }
    @NotBlank(message = "This field can not be blank")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
