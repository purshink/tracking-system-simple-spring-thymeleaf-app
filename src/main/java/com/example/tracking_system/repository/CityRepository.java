package com.example.tracking_system.repository;

import com.example.tracking_system.model.entity.City;
import com.example.tracking_system.model.entity.CityNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    Optional<City> findByName(CityNameEnum name);
}
