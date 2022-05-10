package com.video.rental.njcode.customer.domain;

import com.video.rental.njcode.share.domain.exception.DomainDataException;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class Customer {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private Integer bonusPoints;

    public static Customer createCustomer(String name, String surname, Integer age) {
        return new Customer(null, name, surname, age, 0);
    }

    public static Customer createCustomer(Long id, String name, String surname, Integer age, Integer bonusPoints) {
        return new Customer(id, name, surname, age, bonusPoints);
    }

    private Customer(Long id, String name, String surname, Integer age, Integer bonusPoints) {
        setId(id);
        setName(name);
        setSurname(surname);
        setAge(age);
        setBonusPoints(bonusPoints);
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setName(String name) {
        if (!StringUtils.hasLength(name)) {
            throw new DomainDataException("Name field must contains any character");
        }
        this.name = name;
    }

    private void setSurname(String surname) {
        if (!StringUtils.hasLength(surname)) {
            throw new DomainDataException("Surname field must contains any character");
        }
        this.surname = surname;
    }

    private void setAge(Integer age) {
        if (isProperlyAge(age)){
            throw new DomainDataException("Values of Age field range between 0-100");
        }
        this.age = age;
    }

    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    private boolean isProperlyAge(Integer age) {
        return age < 0 || age > 100;
    }
}
