package com.example.tracking_system.service;

import com.example.tracking_system.model.entity.Country;
import com.example.tracking_system.model.entity.CountryNameEnum;
import com.example.tracking_system.view.CountryViewModel;

import java.util.List;

public interface CountryService {
    Country findCountry(CountryNameEnum name);

    void initCountries();

    List<CountryViewModel> getCountries();
}
