package com.jpraphael.ajubus.services;

import com.jpraphael.ajubus.model.ViagemExtra;
import com.jpraphael.ajubus.utils.GenericDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ViagemExtraService extends AbstractCrudServices<ViagemExtra> {

    @Inject
    private GenericDao<ViagemExtra> dao;

    @Override
    protected GenericDao<ViagemExtra> getDao() {
        return dao;
    }
}
