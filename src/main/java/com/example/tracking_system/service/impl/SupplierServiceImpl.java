package com.example.tracking_system.service.impl;

import com.example.tracking_system.model.binding.SupplierUpdateBindingModel;
import com.example.tracking_system.model.entity.*;
import com.example.tracking_system.model.entity.service.SupplierServiceModel;
import com.example.tracking_system.repository.SupplierRepository;
import com.example.tracking_system.service.CityService;
import com.example.tracking_system.service.CountryService;
import com.example.tracking_system.service.SupplierService;
import com.example.tracking_system.view.SupplierNameView;
import com.example.tracking_system.view.SupplierShortViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;
    private final CityService cityService;
    private final CountryService countryService;

    @Autowired
    public SupplierServiceImpl(ModelMapper modelMapper, SupplierRepository supplierRepository, CityService cityService, CountryService countryService) {
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
        this.cityService = cityService;
        this.countryService = countryService;
    }

    @Override
    public boolean saveSupplier(SupplierServiceModel supplierServiceModel) {
        try {
            Supplier supplier = this.modelMapper.map(supplierServiceModel, Supplier.class);
            City city = this.cityService.findCity(supplierServiceModel.getCity());
            Country country = this.countryService.findCountry(supplierServiceModel.getCountry());
            supplier.setCountry(country);
            supplier.setCity(city);
            this.supplierRepository.save(supplier);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public Supplier findSupplier(String supplier) {
        return this.supplierRepository.findSupplierBySupplierName(supplier).orElse(null);
    }

    @Override
    public Supplier findById(Long id) {
        return this.supplierRepository.findById(id).orElse(null);
    }

    @Override
    public void removeSupplierFromDB(Long id) {
            this.supplierRepository.deleteById(id);
    }

    @Override
    public boolean updateSupplier(SupplierUpdateBindingModel supplierUpdateBindingModel) {

        try {
            Supplier supplier = this.supplierRepository.findSupplierBySupplierName(supplierUpdateBindingModel.getSupplierName()).orElse(null);
            City city = this.cityService.findCity(supplierUpdateBindingModel.getCity());
            Country country = this.countryService.findCountry(supplierUpdateBindingModel.getCountry());
            assert supplier != null;
            supplier.setSupplierName(supplierUpdateBindingModel.getSupplierName());
            supplier.setAddress(supplierUpdateBindingModel.getAddress());
            supplier.setCity(city);
            supplier.setCountry(country);
            supplier.setContactPerson(supplierUpdateBindingModel.getContactPerson());
            supplier.setAddress(supplierUpdateBindingModel.getAddress());
            supplier.setBankName(supplierUpdateBindingModel.getBankName());
            supplier.setIban(supplierUpdateBindingModel.getIban());
            supplier.setBic(supplierUpdateBindingModel.getBic());
            supplier.setCode(supplierUpdateBindingModel.getCode());
            supplier.setPhoneNumber(supplierUpdateBindingModel.getPhoneNumber());
            supplier.setUid(supplierUpdateBindingModel.getUid());
            supplier.setVat(supplierUpdateBindingModel.getVat());
            this.supplierRepository.save(supplier);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean supplierExists(String supplierName) {
        Supplier supplier = this.supplierRepository.findSupplierBySupplierName(supplierName).orElse(null);
        if(supplier == null){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean uidExists(String uid) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByUid(uid);
        if(supplier.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean vatExists(String vat) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByVat(vat);
        if(supplier.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean codeExists(String code) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByCode(code);
        if(supplier.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean ibanExists(String iban) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByIban(iban);
        if(supplier.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean cantUpdateName(String supplierName, Long id) {
            Supplier supplier = this.supplierRepository.findSupplierBySupplierName(supplierName).orElse(null);
            if(supplier == null || supplier.getId().equals(id)){
                return false;
            }
            else {
                return true;
            }
    }

    @Override
    public boolean cantUpdateIban(String iban, Long id) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByIban(iban);
        if(supplier.isEmpty()  || supplier.get().getId().equals(id)){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean cantUpdateUid(String uid, Long id) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByUid(uid);
        if(supplier.isEmpty()  || supplier.get().getId().equals(id)){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean cantUpdateCode(String code, Long id) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByCode(code);
        if(supplier.isEmpty()  || supplier.get().getId().equals(id)){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean cantUpdateVat(String vat, Long id) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByVat(vat);
        if(supplier.isEmpty()  || supplier.get().getId().equals(id)){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public List<SupplierShortViewModel> findAll() {
        return this.supplierRepository.findAll()
                .stream()
                .map(supplier -> this.modelMapper.map(supplier, SupplierShortViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public Long findSupplierId(String supplierName) {
        Optional<Supplier> bySupplierName = this.supplierRepository.findSupplierBySupplierName(supplierName);

        return bySupplierName.map(BaseEntity::getId).orElse(null);
    }

}
