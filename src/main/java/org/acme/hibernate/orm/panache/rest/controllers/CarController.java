package org.acme.hibernate.orm.panache.rest.controllers;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import org.acme.hibernate.orm.panache.rest.exceptions.RestApplicationException;
import org.acme.hibernate.orm.panache.rest.models.Car;
import org.acme.hibernate.orm.panache.rest.services.CarService;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("")
public class CarController {

    @Inject
    private CarService carService;
    @Inject
    Template home;
    @Inject
    Template create;
    @Inject
    Template get;
    @Inject
    Template update;


    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getCreate() {
        return create.instance();
    }


    @GET
    @Path("/update/{id}")
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance getUpdate(Long id) throws RestApplicationException {
        return update.data("car", carService.getById(id));
    }


    @GET
    @Path("/cars")
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance getHome() {
        List<Car> cars = carService.getAll();
        return home.data("cars", cars);
    }


    @POST
    @Path("/cars")
    @Transactional
    @ResponseStatus(201)
    public TemplateInstance create(@RestForm String brand, @RestForm String model, @RestForm String country, @RestForm int price) {
        Car car = new Car(brand, model, country, price);
        carService.create(car);
        return getHome();
    }


    @GET
    @Path("/cars/{id}")
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance getById(Long id) throws RestApplicationException {
        return get.data("car", carService.getById(id));
    }


    @POST
    @Path("/update/{id}")
    @Transactional
    public TemplateInstance updateById(Long id, @RestForm String brand, @RestForm String model, @RestForm String country, @RestForm int price)
            throws RestApplicationException {
        Car car = new Car(brand, model, country, price);
        carService.updateById(car, id);
        return getById(id);
    }


    @POST
    @Path("/delete/{id}")
    @Transactional
    public TemplateInstance deleteById(Long id) throws RestApplicationException {
        carService.deleteById(id);
        return getHome();
    }
}
