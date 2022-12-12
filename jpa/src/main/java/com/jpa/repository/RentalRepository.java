package com.jpa.repository;

import com.jpa.dto.MovieRentalDto;
import com.jpa.dto.MovieRentalInterface;
import com.jpa.dto.MovieRentalRecord;
import com.jpa.dto.PaymentDto;
import com.jpa.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {
    @Query(nativeQuery = true, value = """
            SELECT C.title, A.rental_date
            FROM rental A
                INNER JOIN inventory B ON B.inventory_id = A.inventory_id
                INNER JOIN film C ON C.film_id = B.film_id
            WHERE C.title LIKE %:title%
            ORDER BY C.title
        """)
    List<MovieRentalInterface> getRentalMoviesProjection(@Param("title") String title);

    @Query(nativeQuery = true, value = """
            SELECT C.title, A.rental_date
            FROM rental A
                INNER JOIN inventory B ON B.inventory_id = A.inventory_id
                INNER JOIN film C ON C.film_id = B.film_id
            WHERE C.title LIKE %:title%
            ORDER BY C.title
        """)
    List<Tuple> getRentalMoviesTuple(@Param("title") String title);

    @Query(nativeQuery = true, value = """
            SELECT C.title, A.rental_date
            FROM rental A
                INNER JOIN inventory B ON B.inventory_id = A.inventory_id
                INNER JOIN film C ON C.film_id = B.film_id
            WHERE C.title LIKE %:title%
            ORDER BY C.title
        """)
    List<MovieRentalRecord> getRentalMoviesRecord(@Param("title") String title);

    @Query(value = """
            select new com.jpa.dto.MovieRentalDto(
                C.title, A.rentalDate
            )
            FROM RentalEntity A
                INNER JOIN InventoryEntity B ON B.inventoryId = A.inventoryId
                INNER JOIN FilmEntity C ON C.filmId = B.filmId
            WHERE C.title LIKE %:title%
            ORDER BY C.title
        """)
    List<MovieRentalDto> getRentalMoviesDto(@Param("title") String title);

    @Query(nativeQuery = true, value = """
            SELECT A.customer_id, A.payment_id,
                LAG(A.payment_id, 1) over (ORDER BY A.payment_id) AS "prev_lag",
                FIRST_VALUE(A.payment_id) OVER(ORDER BY A.payment_id ASC ROWS 1 PRECEDING) AS "prev",
                FIRST_VALUE(A.payment_id) OVER(ORDER BY A.payment_id DESC ROWS 1 PRECEDING) AS "next",
                LEAD(A.payment_id, 1) OVER (PARTITION BY A.customer_id ORDER BY A.payment_id asc) AS next_by_group,
                ROW_NUMBER() OVER (PARTITION BY A.customer_id ORDER BY A.payment_id) AS row_no,
                COUNT(*) OVER (PARTITION BY A.customer_id) AS "count",
                DENSE_RANK() OVER(PARTITION BY A.customer_id ORDER BY A.payment_id) AS "rank",
                A.rental_id, ROUND(A.rental_id/1000, 2) AS "round",
                B.first_name, LEFT(B.first_name, 1) AS left_name
            FROM payment A
                LEFT JOIN customer B USING(customer_id)
            ORDER BY payment_id
        """)
    List<PaymentDto> windowFunctions();
}
