package com.example.tracking_system.service.impl;

import com.example.tracking_system.model.binding.MaterialUpdateBindingModel;
import com.example.tracking_system.model.entity.Material;
import com.example.tracking_system.model.entity.Supplier;
import com.example.tracking_system.model.entity.service.MaterialServiceModel;
import com.example.tracking_system.repository.MaterialRepository;
import com.example.tracking_system.service.MaterialService;
import com.example.tracking_system.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final ModelMapper modelMapper;
    private final MaterialRepository materialRepository;
    private final SupplierService supplierService;

    @Autowired
    public MaterialServiceImpl(ModelMapper modelMapper, MaterialRepository materialRepository, SupplierService supplierService) {
        this.modelMapper = modelMapper;
        this.materialRepository = materialRepository;
        this.supplierService = supplierService;
    }

    @Override
    public boolean saveMaterial(MaterialServiceModel materialServiceModel) {
        try {

            Material material = this.modelMapper.map(materialServiceModel, Material.class);
            Supplier supplier = this.supplierService.findSupplier(materialServiceModel.getSupplier());
            if (supplier == null){
                return false;
            }
            else {
                Optional<Material> byNameAndAndSupplier = this.materialRepository.findByNameAndAndSupplier(materialServiceModel.getName(), supplier);
                if (byNameAndAndSupplier.isPresent()) {
                    Double mole = byNameAndAndSupplier.get().getMole();
                    mole += materialServiceModel.getMole();
                    byNameAndAndSupplier.get().setMole(mole);
                    this.materialRepository.save(byNameAndAndSupplier.get());
                } else {
                    material.setSupplier(supplier);
                    this.materialRepository.save(material);
                }
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public List<Material> getAllMaterials() {
        return this.materialRepository.findAll();
    }

    @Override
    public void removeMaterialFromDB(Long id) {
        this.materialRepository.deleteById(id);
    }

    @Override
    public Material findMaterialById(Long id) {
        return this.materialRepository.findById(id).orElse(null);
    }



    @Override
    public boolean updateMaterial(MaterialUpdateBindingModel materialUpdateBindinModel) {

        try {
            Material material = this.materialRepository.findById(materialUpdateBindinModel.getId()).orElse(null);
            assert material != null;
            material.setName(materialUpdateBindinModel.getName());
            material.setMole(materialUpdateBindinModel.getMole());
            this.materialRepository.save(material);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void findIfSupplierHasMaterialsInDBAndDelete(Long id) {
        List<Material> allBySupplierId = this.materialRepository.findAllBySupplierId(id);
        this.materialRepository.deleteAll(allBySupplierId);

    }
}
