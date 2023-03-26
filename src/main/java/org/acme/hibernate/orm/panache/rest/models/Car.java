package org.acme.hibernate.orm.panache.rest.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String brand;
    @Column(nullable = false)
    public String model;
    @Column(nullable = false)
    public String country;
    @Column(nullable = false)
    public Integer price;


    public Car(){}


    public Car(String brand, String model, String country, Integer price) {
        this.brand = brand;
        this.model = model;
        this.country = country;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                '}';
    }
}
