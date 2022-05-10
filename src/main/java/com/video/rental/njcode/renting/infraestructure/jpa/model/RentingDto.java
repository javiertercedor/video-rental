package com.video.rental.njcode.renting.infraestructure.jpa.model;


import com.video.rental.njcode.customer.infraestructure.jpa.model.CustomerDto;
import com.video.rental.njcode.film.infraestructure.jpa.model.FilmDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class RentingDto {

    @Id
    @GeneratedValue
    private Long id;
    @Min(0)
    private Integer numberOfDays;
    @Min(0)
    private Double totalPrice;
    @Min(0)
    private Double lateCharge;
    @CreatedDate
    private Date initDate;

    @ManyToOne
    private CustomerDto customer;
    @OneToMany
    private List<FilmDto> films;
}
