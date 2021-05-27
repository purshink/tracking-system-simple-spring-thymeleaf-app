package com.example.tracking_system.model.entity.service;

public class MaterialServiceModel {
    private String name;
    private Double mole;
    private String supplier;

    public MaterialServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMole() {
        return mole;
    }

    public void setMole(Double mole) {
        this.mole = mole;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
