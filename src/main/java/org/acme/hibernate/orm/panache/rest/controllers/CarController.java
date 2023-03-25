package org.acme.hibernate.orm.panache.rest.controllers;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.hibernate.orm.panache.rest.exceptions.RestApplicationException;
import org.acme.hibernate.orm.panache.rest.models.Car;
import org.acme.hibernate.orm.panache.rest.services.CarService;
import org.jboss.resteasy.reactive.ResponseStatus;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/cars")
public class CarController {

    @Inject
    private CarService carService;


    @POST
    @Transactional
    public void create(Car car) {
        carService.create(car);
    }


    @GET
    @Path("{id}")
    @Produces("application/json")
    public Car getById(Long id) throws RestApplicationException {
        return carService.getById(id);
    }


    @PATCH
    @Path("{id}")
    @Transactional
    public void updateById(Car car, Long id) throws RestApplicationException {
        carService.updateById(car, id);
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public void deleteById(Long id) throws RestApplicationException {
        carService.deleteById(id);
    }
}
