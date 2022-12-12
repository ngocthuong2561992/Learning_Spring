package com.multidb.repository.sakila;

import com.multidb.repository.sakila.dto.MovieRentalInfo;
import com.multidb.repository.sakila.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {

    @Query(nativeQuery = true, value = "" +
            "SELECT C.title, A.rental_date " +
            "FROM rental A " +
            "   INNER JOIN inventory B ON B.inventory_id = A.inventory_id " +
            "   INNER JOIN film C ON C.film_id = B.film_id " +
            "WHERE C.title LIKE %:title% " +
            "ORDER BY C.title ")
    List<MovieRentalInfo> getRentalMovies(@Param("title") String title);
}
