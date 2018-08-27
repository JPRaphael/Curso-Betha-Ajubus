package com.jpraphael.ajubus.utils;

import com.jpraphael.ajubus.model.Entidade;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Producers {
    @Produces
    @PersistenceContext
    private EntityManager em;

    @Produces
    @Dependent
    public <T extends Entidade> GenericDao<T> produceDao(InjectionPoint injectionPoint, EntityManager em) {
        Type[] args = ((ParameterizedType)injectionPoint.getType()).getActualTypeArguments();
        if (args.length == 0)
            throw new IllegalArgumentException("O GenericDao precisa de um tipo");
        Class<T> type = (Class<T>) args[0];
        return new GenericDao(em, type);
    }
}
