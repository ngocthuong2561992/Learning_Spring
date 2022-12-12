package com.jpa.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jpa.entity.relationship.FilmEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "client")
public class ClientEntity implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue( strategy= GenerationType.AUTO, generator="native" )
    @GenericGenerator( name = "native", strategy = "native" )
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
//    @SequenceGenerator(allocationSize = 10, name = "sequence")
    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
