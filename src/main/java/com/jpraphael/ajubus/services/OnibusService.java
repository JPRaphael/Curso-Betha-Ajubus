package com.jpraphael.ajubus.services;

import com.jpraphael.ajubus.model.Manutencao;
import com.jpraphael.ajubus.model.Onibus;
import com.jpraphael.ajubus.utils.GenericDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.ws.rs.PathParam;
import java.util.List;

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

