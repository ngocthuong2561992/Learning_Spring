package com.replicadb.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.replicadb.entity.RentalNewEntity;
import com.replicadb.repository.RentalNewRepository;
import com.replicadb.service.ActorService;
import com.replicadb.service.ApiService;
import com.replicadb.service.RentalNewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ApiServiceImpl implements ApiService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("customObjectMapper")
    private ObjectMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RentalNewRepository rentalNewRepository;

    @Autowired
    private ActorService actorService;

    @Autowired
    private RentalNewService rentalNewService;

    @Override
    public <T> T getSlave() {
        Optional<RentalNewEntity> entity = rentalNewRepository.findById(1);
        return (T) entity.get();
    }

    @Override
    @Transactional
    public void saveMaster() {
        int postfix = 8;
        try {
            RentalNewEntity entity = rentalNewRepository.getReferenceById(1);
            entity.setStaffId(postfix);
            rentalNewRepository.save(entity);
            int a = 1/0;
        }catch (Exception e) {
            actorService.saveActor("THORA " + postfix);
            throw e;
        }
    }
}
