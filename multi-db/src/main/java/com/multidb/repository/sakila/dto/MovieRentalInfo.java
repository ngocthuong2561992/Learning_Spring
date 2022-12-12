package com.multidb.repository.sakila.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface MovieRentalInfo {
    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.rental_date}")
    LocalDateTime getRentalDate();

}
