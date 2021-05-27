package com.example.tracking_system.repository;

import com.example.tracking_system.model.entity.City;
import com.example.tracking_system.model.entity.Country;
import com.example.tracking_system.model.entity.CountryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(CountryNameEnum name);
}
