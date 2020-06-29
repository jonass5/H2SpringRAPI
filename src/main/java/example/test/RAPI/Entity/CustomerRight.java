package example.test.RAPI.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import example.test.RAPI.JsonDeserializer.CustomerRightJsonDeserializer;
import example.test.RAPI.JsonSerializer.CustomerRightsJsonSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "Customerright")
@JsonSerialize(using = CustomerRightsJsonSerializer.class)
@JsonDeserialize(using = CustomerRightJsonDeserializer.class)
public class CustomerRight {

    public int getCustomerrightid() {
        return customerrightid;
    }

    public void setCustomerrightid(int customerrightid) {
        this.customerrightid = customerrightid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUSTOMERRIGHTID")
    public int customerrightid;

    @OneToMany(mappedBy = "customerid")
    private Set<Customer> customers;

    @NotBlank(message = "{NotBlank.customerright.name}")
    @Column(name = "NAME")
    private String name;

    public Set<Customer> getCustomers() {
        return customers;
    }

    public CustomerRight(String name) {
        this.name = name;
    }

    public CustomerRight(int customerrightid, String name) {
        this.customerrightid = customerrightid;
        this.name = name;
    }

    public CustomerRight() {
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
