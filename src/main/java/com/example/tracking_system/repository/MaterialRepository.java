package com.example.tracking_system.repository;

import com.example.tracking_system.model.entity.Material;
import com.example.tracking_system.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    Optional<Material> findByNameAndAndSupplier(String name, Supplier supplier);
    List<Material> findAllBySupplierId(Long id);
}
