/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.polygotweb.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import org.omnifaces.cdi.ViewScoped;
import org.waastad.polygot.nosql.Person;
import org.waastad.polygot.relational.Customer;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Stateless
@Named
public class ViewController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Customer> customers = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
//    @Inject
//    private EntityManager em;
    @PersistenceUnit(unitName = "composite-pu")
    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        System.out.println("Fetching.....");
//        TypedQuery<Customer> query = em.createQuery("SELECT t FROM Customer t", Customer.class);
//        customers = query.getResultList();
//        TypedQuery<Person> query2 = em.createQuery("SELECT t FROM Person t", Person.class);
//        persons = query2.getResultList();
    }

    public void updateTables(ActionEvent event) {
        System.out.println("Fetching.....");
        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        TypedQuery<Customer> query = em.createQuery("SELECT t FROM Customer t", Customer.class);
//        customers = query.getResultList();

        TypedQuery<Person> query2 = em.createQuery("SELECT t FROM Person t", Person.class);
        persons = query2.getResultList();
//        em.getTransaction().commit();
    }

    public void addCustomer(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer c = new Customer("kunde-" + new Date().toString());
        em.persist(c);
        em.getTransaction().commit();
    }

    public void addPerson(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person p = new Person("kunde-" + new Date().toString());
        em.persist(p);
        em.flush();
        em.close();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}
