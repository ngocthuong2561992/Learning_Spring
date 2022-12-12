package com.jpa.entity.relationship;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "country")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country")
    private String country;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(
            mappedBy = "country",
//            cascade = CascadeType.ALL,
//            orphanRemoval = false,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<CityEntity> cities = new ArrayList<>();

}
