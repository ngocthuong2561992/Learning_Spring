package com.jpa.dao;

import com.jpa.dto.CityDto;
import com.jpa.dto.MovieRentalDto;
import com.jpa.dto.PropertyDto;
import com.jpa.entity.relationship.ActorEntity;
import org.springframework.data.repository.query.Param;

import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.List;

public interface RentalDao {
    List<MovieRentalDto> getRentalMovies(String title);
    List<CityDto> getCommonTableExpression();
    List<PropertyDto> mapRowToColumn();
    List<CityDto> getPartition();

    ActorEntity findActorWithLock(int id);
    List<ActorEntity> findActorForJobQueueSkipLock();
}
