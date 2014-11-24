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
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;
import org.waastad.polygot.nosql.Person;
import org.waastad.polygot.relational.Customer;
import org.waastad.polygotweb.repository.CustomerRepository;
import org.waastad.polygotweb.repository.PersonRepository;

/**
 *
 * @author Helge Waastad <helge.waastad@waastad.org>
 */
@ViewScoped
@Named
public class ViewController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Customer> customers = new ArrayList<>();
    private Customer customer;
    private List<Person> persons = new ArrayList<>();
    private Person person;

    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private PersonRepository personRepository;

    @PostConstruct
    public void init() {
        System.out.println("Initial Fetching.....");
        customers = customerRepository.findAll();
        persons = personRepository.findAll();
    }

    public void updateTables(ActionEvent event) {
        System.out.println("Fetching.....");
        customers = customerRepository.findAll();
        persons = personRepository.findAll();
    }

    public void addCustomer(ActionEvent event) {
        Customer c = new Customer("customer-" + new Date().toString());
        customers.add(customerRepository.save(c));
        Messages.addGlobalInfo("Customer saved");
    }

    public void deleteCustomer(ActionEvent event) {
        try {
            customer = customerRepository.findBy(customer.getId());
            customerRepository.remove(customer);
            customers.remove(customer);
            Messages.addGlobalInfo("Customer deleted");
        } catch (Exception e) {
            Messages.addGlobalError(e.getMessage());
        }
    }

    public void deletePerson(ActionEvent event) {
        person = personRepository.findBy(person.getId());
        personRepository.remove(person);
        persons.remove(person);
        Messages.addGlobalInfo("Person deleted");
    }

    public void addPerson(ActionEvent event) {

        if (customer == null) {
            Messages.addGlobalWarn("Please select customer");
        } else {
            Person p = new Person("person-" + new Date().toString());
            p.setCustomer(customer);
            persons.add(personRepository.save(p));
            Messages.addGlobalInfo("Person saved");
        }
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
