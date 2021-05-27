package com.example.tracking_system.service;

import com.example.tracking_system.model.binding.SupplierUpdateBindingModel;
import com.example.tracking_system.model.entity.Supplier;
import com.example.tracking_system.model.entity.service.SupplierServiceModel;
import com.example.tracking_system.view.SupplierNameView;
import com.example.tracking_system.view.SupplierShortViewModel;


import java.util.List;

public interface SupplierService {
    boolean saveSupplier(SupplierServiceModel supplierServiceModel);

    Supplier findSupplier(String supplier);

    Supplier findById(Long id);

    void removeSupplierFromDB(Long id);

    boolean updateSupplier(SupplierUpdateBindingModel supplierUpdateBindingModel);


    boolean supplierExists(String supplierName);

    boolean uidExists(String uid);

    boolean vatExists(String vat);

    boolean codeExists(String code);

    boolean ibanExists(String iban);


    boolean cantUpdateName(String supplierName, Long id);

    boolean cantUpdateIban(String iban, Long id);

    boolean cantUpdateUid(String uid, Long id);

    boolean cantUpdateCode(String code, Long id);

    boolean cantUpdateVat(String vat, Long id);

    List<SupplierShortViewModel> findAll();

    Long findSupplierId(String supplierName);
}
