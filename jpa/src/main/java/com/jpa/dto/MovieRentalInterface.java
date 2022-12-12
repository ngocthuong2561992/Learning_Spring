package com.jpa.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface MovieRentalInterface {
    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.rental_date}")
    LocalDateTime getRentalDate();

}
