package com.example.tracking_system.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MaterialUpdateBindingModel {
    private Long id;
    private String name;
    private Double mole;


    public MaterialUpdateBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
