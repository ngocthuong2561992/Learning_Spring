package com.jpa.service.impl;

import com.jpa.entity.relationship.ActorEntity;
import com.jpa.enumerator.Gender;
import com.jpa.repository.ActorRepository;
import com.jpa.repository.RentalRepository;
import com.jpa.service.ActorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActorServiceImpl implements ActorService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ActorRepository actorRepository;

    @Override
    @Transactional(
        isolation = Isolation.SERIALIZABLE,
//        transactionManager = "transactionManager",
//        rollbackFor = Exception.class,
//        noRollbackFor = Exception.class,
//        timeout = 60,
        propagation = Propagation.NOT_SUPPORTED
    )
    public void saveActor(String firstName) {
        ActorEntity entity = actorRepository.findById(200).get();
        entity.setFirstName(firstName);
        actorRepository.save(entity);
    }

    @Override
    @Transactional(
        isolation = Isolation.SERIALIZABLE,
        rollbackFor = Exception.class,
        noRollbackFor = ArithmeticException.class,
        propagation = Propagation.REQUIRES_NEW
    )
    public void saveActorRollbackFor(String firstName) {
        ActorEntity entity = actorRepository.findById(200).get();
        entity.setFirstName(firstName);
        actorRepository.save(entity);
    }
}
