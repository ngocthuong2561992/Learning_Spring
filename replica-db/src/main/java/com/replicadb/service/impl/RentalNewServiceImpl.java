package com.replicadb.service.impl;

import com.replicadb.entity.ActorEntity;
import com.replicadb.entity.RentalNewEntity;
import com.replicadb.repository.RentalNewRepository;
import com.replicadb.service.RentalNewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RentalNewServiceImpl implements RentalNewService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RentalNewRepository rentalNewRepository;

    @Override
    @Transactional
    public void saveRental(int StaffId) {
        RentalNewEntity entity = rentalNewRepository.getReferenceById(1);
        entity.setStaffId(StaffId);
        rentalNewRepository.save(entity);
    }
}
