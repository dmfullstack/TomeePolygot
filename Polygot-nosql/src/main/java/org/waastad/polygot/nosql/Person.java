/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.polygot.nosql;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.NoSql;
import org.waastad.polygot.relational.Customer;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@NamedQueries({
    @NamedQuery(name = Person.FIND_ALL, query = "SELECT t FROM Person t")
})
@Entity
@NoSql(dataType = "persons", dataFormat = DataFormatType.MAPPED)
public class Person implements Serializable {

    public static final String FIND_ALL = "Person.FindAll";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "_id")
    private String id;
    @Column(name = "name")
    private String name;
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer")
    private Customer customer;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "org.waastad.polygot.nosql.Person[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
