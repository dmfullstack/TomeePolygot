/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.polygotweb.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceUnit(unitName = "composite-pu")
    private EntityManagerFactory emf;

    @Produces
    @RequestScoped
    public EntityManager create() {
        System.out.println("Producing entitymanager....");
        return emf.createEntityManager();
    }

    public void dispose(@Disposes EntityManager em) {
        if (em.isOpen()) {
            System.out.println("Disposing entitymanager....");
            em.close();
        }
    }
}
