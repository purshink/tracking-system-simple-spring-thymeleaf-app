package com.example.tracking_system.init;

import com.example.tracking_system.service.CityService;
import com.example.tracking_system.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBinit implements CommandLineRunner {
    private final CityService cityService;
    private final CountryService countryService;

    @Autowired
    public DBinit(CityService cityService, CountryService countryService) {
        this.cityService = cityService;
        this.countryService = countryService;
    }


    @Override
    public void  run(String... args)    {
        this.cityService.initCities();
        this.countryService.initCountries();
    }
}
