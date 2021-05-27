package com.example.tracking_system.service;

import com.example.tracking_system.model.binding.MaterialUpdateBindingModel;
import com.example.tracking_system.model.entity.Material;
import com.example.tracking_system.model.entity.service.MaterialServiceModel;

import java.util.List;

public interface MaterialService {
    boolean saveMaterial(MaterialServiceModel materialServiceModel);

    List<Material> getAllMaterials();

    void removeMaterialFromDB(Long id);

    Material findMaterialById(Long id);



    boolean updateMaterial(MaterialUpdateBindingModel materialUpdateBindinModel);

    void findIfSupplierHasMaterialsInDBAndDelete(Long id);
}
