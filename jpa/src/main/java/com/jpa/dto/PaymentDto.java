package com.jpa.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PaymentDto {
    @Value("#{target.customer_id}")
    Integer getCustomerId();

    @Value("#{target.payment_id}")
    Integer getPaymentId();

    @Value("#{target.prev_lag}")
    Integer getPrevLag();

    @Value("#{target.prev}")
    Integer getPrev();

    @Value("#{target.next}")
    Integer getNext();

    @Value("#{target.next_by_group}")
    Integer getNextByGroup();

    @Value("#{target.row_no}")
    Integer getRowNo();

    @Value("#{target.count}")
    Integer getCount();

    @Value("#{target.rental_id}")
    Integer getRentalId();

    @Value("#{target.rank}")
    Integer getRank();

    @Value("#{target.round}")
    Float getRound();

    @Value("#{target.first_name}")
    String getFirstName();

    @Value("#{target.left_name}")
    String getLeftName();

}
