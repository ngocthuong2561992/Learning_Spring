package com.jpa.service.impl;

import com.jpa.entity.relationship.CityEntity;
import com.jpa.repository.CityRepository;
import com.jpa.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityServiceImpl implements CityService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional
    public void saveCity(String cityName) {
        CityEntity entity = cityRepository.findById(600).get();
        entity.setCity(cityName);
        cityRepository.save(entity);
    }
}
