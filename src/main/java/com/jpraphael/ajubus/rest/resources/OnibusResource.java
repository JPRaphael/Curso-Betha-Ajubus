package com.jpraphael.ajubus.rest.resources;

import com.jpraphael.ajubus.model.Onibus;
import com.jpraphael.ajubus.rest.AbstractCrudResource;
import com.jpraphael.ajubus.services.AbstractCrudServices;
import com.jpraphael.ajubus.services.OnibusService;

import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("onibus")
public class OnibusResource extends AbstractCrudResource<Onibus> {

    @Inject
    private OnibusService service;

    @Override
    protected AbstractCrudServices<Onibus> getService() {
        return service;
    }

}
