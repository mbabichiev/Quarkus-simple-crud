package org.acme.hibernate.orm.panache.rest.services;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.hibernate.orm.panache.rest.exceptions.RestApplicationException;
import org.acme.hibernate.orm.panache.rest.models.Car;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarService {


    public static final String ERROR_TEMPLATE = "Car with id: %s not found!";


    public void create(Car car) {
        car.persist();
    }


    public Car getById(Long id) throws RestApplicationException {
        Car car = Car.findById(id);
        if(car == null) {
            throw new RestApplicationException(String.format(ERROR_TEMPLATE, id));
        }
        return car;
    }


    public void updateById(Car car, Long id) throws RestApplicationException {
        Car oldCar = Car.findById(id);
        if(oldCar == null) {
            throw new RestApplicationException(String.format(ERROR_TEMPLATE, id));
        }

        oldCar.brand = car.brand;
        oldCar.model = car.model;
        oldCar.country = car.country;
        oldCar.price = car.price;
    }


    public void deleteById(Long id) throws RestApplicationException {
        Car oldCar = Car.findById(id);
        if(oldCar == null) {
            throw new RestApplicationException(String.format(ERROR_TEMPLATE, id));
        }

        oldCar.delete();
    }

}
