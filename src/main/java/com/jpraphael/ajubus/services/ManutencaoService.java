package com.jpraphael.ajubus.services;

import com.jpraphael.ajubus.model.Manutencao;
import com.jpraphael.ajubus.utils.GenericDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ManutencaoService extends AbstractCrudServices<Manutencao> {

    @Inject
    private GenericDao<Manutencao> dao;

    @Override
    protected GenericDao<Manutencao> getDao() {
        return dao;
    }
}
