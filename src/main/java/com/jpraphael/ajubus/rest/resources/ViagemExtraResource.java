package com.jpraphael.ajubus.rest.resources;

import com.jpraphael.ajubus.model.ViagemExtra;
import com.jpraphael.ajubus.rest.AbstractCrudResource;
import com.jpraphael.ajubus.services.AbstractCrudServices;
import com.jpraphael.ajubus.services.ViagemExtraService;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("viagem-extra")
public class ViagemExtraResource extends AbstractCrudResource<ViagemExtra> {

    @Inject
    private ViagemExtraService service;

    @Override
    protected AbstractCrudServices<ViagemExtra> getService() {
        return service;
    }

}

