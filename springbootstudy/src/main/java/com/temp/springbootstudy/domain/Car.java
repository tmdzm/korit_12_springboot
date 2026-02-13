package com.temp.springbootstudy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {//Car가 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 컬럼명

    private String brand, model, registrationNumber;

    private int modelYear, price ;

    public Car() {}

    public Car(String brand, String model, String registrationNumber, int modelYear, int price) {

        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.modelYear = modelYear;
        this.price = price;
    }
}
