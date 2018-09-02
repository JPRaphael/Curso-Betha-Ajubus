package com.jpraphael.ajubus.services;

import com.jpraphael.ajubus.model.Onibus;
import com.jpraphael.ajubus.utils.GenericDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OnibusService extends AbstractCrudServices<Onibus> {

    @Inject
    private GenericDao<Onibus> dao;

    @Override
    protected GenericDao<Onibus> getDao() {
        return dao;
    }
}

