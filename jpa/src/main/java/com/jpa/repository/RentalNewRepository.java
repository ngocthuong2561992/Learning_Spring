package com.jpa.repository;

import com.jpa.dto.MovieRentalDto;
import com.jpa.dto.MovieRentalInterface;
import com.jpa.dto.MovieRentalRecord;
import com.jpa.entity.RentalEntity;
import com.jpa.entity.RentalNewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface RentalNewRepository extends JpaRepository<RentalNewEntity, Integer> {
}
