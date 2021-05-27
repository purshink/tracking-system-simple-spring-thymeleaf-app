package com.example.tracking_system.service.impl;

import com.example.tracking_system.model.entity.City;
import com.example.tracking_system.model.entity.CityNameEnum;
import com.example.tracking_system.model.entity.Country;
import com.example.tracking_system.repository.CityRepository;
import com.example.tracking_system.service.CityService;
import com.example.tracking_system.view.CityViewModel;
import com.example.tracking_system.view.CountryViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public City findCity(CityNameEnum city) {

        Optional<City> byName = this.cityRepository.findByName(city);

        return byName.orElse(null);

    }

    @Override
    public void initCities() {
        if(this.cityRepository.count() == 0){
           City pld = new City();
            pld.setName(CityNameEnum.Berlin);
            pld.setPostcode(4000);
            this.cityRepository.save(pld);

            City sf = new City();
            sf.setName(CityNameEnum.London);
            sf.setPostcode(1000);
            this.cityRepository.save(sf);

        }
    }

    @Override
    public List<CityViewModel> getCities() {
        List<City> all = this.cityRepository.findAll();
        return all.stream().map(city -> this.modelMapper.map(city, CityViewModel.class)).collect(Collectors.toList());
    }
}
