package com.example.tracking_system.model.binding;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MaterialBindingModel {
    private String name;
    private Double mole;
    private String supplier;

    public MaterialBindingModel() {
    }
    @NotBlank(message = "This field can not be blank")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull(message = "This field can not be blank")
    public Double getMole() {
        return mole;
    }

    public void setMole(Double mole) {
        this.mole = mole;
    }
    @NotBlank(message = "Please choose a supplier")
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
