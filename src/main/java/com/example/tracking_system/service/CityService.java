package com.example.tracking_system.service;

import com.example.tracking_system.model.entity.City;
import com.example.tracking_system.model.entity.CityNameEnum;
import com.example.tracking_system.view.CityViewModel;

import java.util.List;

public interface CityService {
    City findCity(CityNameEnum city);

    void initCities();

    List<CityViewModel> getCities();
}
