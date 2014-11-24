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
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.omnifaces.cdi.ViewScoped;
import org.waastad.polygot.nosql.Person;
import org.waastad.polygot.relational.Customer;
import org.waastad.polygotweb.repository.CustomerRepository;
import org.waastad.polygotweb.repository.PersonRepository;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@SessionScoped
@Named
public class ViewController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Customer> customers;// = new ArrayList<>();
    private List<Person> persons;// = new ArrayList<>();
    @Inject
    private EntityManager em;
//    @PersistenceUnit(unitName = "composite-pu")
//    private EntityManagerFactory emf;

    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private PersonRepository personRepository;

    @PostConstruct
    public void init() {
        System.out.println("Fetching.....");
//        customers = customerRepository.findAll();
//        persons = personRepository.findAll();
    }

    public void updateTables(ActionEvent event) {
        System.out.println("Fetching.....");
        customers = customerRepository.findAll();
        persons = personRepository.findAll();
    }

    public void addCustomer(ActionEvent event) {
        Customer c = new Customer("kunde-" + new Date().toString());
        customerRepository.save(c);

    }

    public void addPerson(ActionEvent event) {
        Person p = new Person("kunde-" + new Date().toString());
        personRepository.save(p);
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
