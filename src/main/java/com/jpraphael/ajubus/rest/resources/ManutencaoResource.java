package com.jpraphael.ajubus.rest.resources;

import com.jpraphael.ajubus.model.Manutencao;
import com.jpraphael.ajubus.rest.AbstractCrudResource;
import com.jpraphael.ajubus.services.AbstractCrudServices;
import com.jpraphael.ajubus.services.ManutencaoService;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("manutencao")
public class ManutencaoResource extends AbstractCrudResource<Manutencao> {

    @Inject
    private ManutencaoService service;

    @Override
    protected AbstractCrudServices<Manutencao> getService() {
        return service;
    }

}

