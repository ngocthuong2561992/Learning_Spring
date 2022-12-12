package com.replicadb.repository;

import com.replicadb.entity.RentalNewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalNewRepository extends JpaRepository<RentalNewEntity, Integer> {
}
