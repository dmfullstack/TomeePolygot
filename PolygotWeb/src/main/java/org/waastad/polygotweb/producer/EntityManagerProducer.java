/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.polygotweb.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Helge Waastad <helge.waastad@waastad.org>
 */
@ApplicationScoped
public class EntityManagerProducer {

//    @PersistenceContext(unitName = "composite-pu")
//    private EntityManager em;
//
//    @Produces
//    @RequestScoped
//    public EntityManager create() {
//        System.out.println("Producing entitymanager: " + em);
//        return this.em;
//    }
    @PersistenceUnit(unitName = "composite-pu")
    private EntityManagerFactory emf;
    
    @Produces
    @Default
    @RequestScoped
    public EntityManager create() {
        System.out.println("Producing entitymanager....");
        return emf.createEntityManager();
    }

    public void dispose(@Disposes @Default EntityManager em) {
        if (em.isOpen()) {
            System.out.println("Disposing entitymanager....");
            em.close();
        }
    }
}
