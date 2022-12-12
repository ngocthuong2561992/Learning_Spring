package com.jpa.service.impl;

import com.jpa.entity.relationship.CountryEntity;
import com.jpa.repository.CountryRepository;
import com.jpa.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryServiceImpl implements CountryService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CountryRepository countryRepository;

    @Override
    @Transactional
    public void saveCountry(String countryName) {
        CountryEntity entity = countryRepository.findById(109).get();
        entity.setCountry(countryName);
        countryRepository.save(entity);
    }
}
