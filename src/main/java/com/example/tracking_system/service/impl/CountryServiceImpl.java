package com.example.tracking_system.service.impl;

import com.example.tracking_system.model.entity.Country;
import com.example.tracking_system.model.entity.CountryNameEnum;
import com.example.tracking_system.repository.CountryRepository;
import com.example.tracking_system.service.CountryService;
import com.example.tracking_system.view.CountryViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Country findCountry(CountryNameEnum name) {
        Optional<Country> byName = this.countryRepository.findByName(name);
        return byName.orElse(null);
    }

    @Override
    public void initCountries() {
        if(this.countryRepository.count() == 0){
            Country bg = new Country();
            bg.setName(CountryNameEnum.Germany);
            bg.setCountryCode("+359");
            this.countryRepository.save(bg);

            Country uk = new Country();
            uk.setName(CountryNameEnum.UK);
            uk.setCountryCode("+44");
            this.countryRepository.save(uk);
        }
    }

    @Override
    public List<CountryViewModel> getCountries() {
        List<Country> all = this.countryRepository.findAll();
        return all.stream().map(country -> this.modelMapper.map(country, CountryViewModel.class)).collect(Collectors.toList());
    }

}
