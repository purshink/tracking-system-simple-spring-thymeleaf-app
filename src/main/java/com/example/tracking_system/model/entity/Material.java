package com.example.tracking_system.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "materials")
public class Material extends BaseEntity{

    //imeto na materiala  e edno i sashto za razlichni dostavchici,
    // no za vseki konkreten dostavchik ima unikalna instanciq na obekt "Material" s razlichno kolichestvp
    private String name;
    //mole - kolichestvo nalichen material pri saotvetniq dostavchik(supplier),
    // napravih go double, za da moje da namalqva pri napraveni dostavki i saotvetno da moje da se updeitva
    //ako ne e nujno i nqma nachin da znaem kakvo kolichestvo v nalichnost ima pri dostavchika, moje da se mahne spored men
    private Double mole;
    private Supplier supplier;

    public Material() {
    }

    @Column()
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public Double getMole() {
        return mole;
    }

    public void setMole(Double mole) {
        this.mole = mole;
    }

    @ManyToOne
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
