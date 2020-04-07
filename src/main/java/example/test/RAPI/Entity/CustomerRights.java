package example.test.RAPI.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Customerright")
public class CustomerRights {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int customerrightid;

    @OneToMany(mappedBy = "customerid")
    private Set<Customer> customers;

    @Column(name = "NAME")
    private String name;

    public Set<Customer> getCustomers() {
        return customers;
    }

    public CustomerRights(int customerrightid, String name) {
        this.customerrightid = customerrightid;
        this.name = name;
    }

    public CustomerRights() {
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public int getId() {
        return customerrightid;
    }

    public CustomerRights(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.customerrightid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
