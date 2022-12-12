package com.replicadb.service.impl;

import com.replicadb.entity.ActorEntity;
import com.replicadb.repository.ActorRepository;
import com.replicadb.service.ActorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ActorServiceImpl implements ActorService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ActorRepository actorRepository;

    @Override
    @Transactional(
        isolation = Isolation.SERIALIZABLE,
//        transactionManager = "jpaTxManager",
        rollbackFor = Exception.class,
        noRollbackFor = ArithmeticException.class,
//        timeout = 60
        propagation = Propagation.REQUIRES_NEW
    )
    public void saveActor(String firstName) {
        ActorEntity entity = actorRepository.findById(200).get();
        entity.setFirstName(firstName);
        actorRepository.save(entity);
    }
}
