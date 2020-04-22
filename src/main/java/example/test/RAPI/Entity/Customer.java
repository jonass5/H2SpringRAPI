package example.test.RAPI.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import example.test.RAPI.JsonDeserializer.CustomerJsonDeserialize;
import example.test.RAPI.JsonSerializer.CustomerJsonSerializer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Customer")
@JsonSerialize(using = CustomerJsonSerializer.class)
@JsonDeserialize(using = CustomerJsonDeserialize.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUSTOMERID")
    private int customerid;

    @Column(name = "NAME")
    private String name;

    public Customer(int customerid) {
        this.customerid = customerid;
    }

    @Column(name = "NACHNAME")
    private String nachname;

    @Column(name = "AGE")
    private int age;

    @ManyToOne
    @JoinColumn(name = "customerrightid")
    private CustomerRight customerRights;

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getCustomerid() {
        return customerid;
    }

    @OneToMany(mappedBy = "orderid")
    private Set<Order> orderList;

    public Set<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<Order> orderList) {
        this.orderList = orderList;
    }

    public CustomerRight getCustomerRights() {
        return customerRights;
    }

    public void setCustomerRights(CustomerRight customerRights) {
        this.customerRights = customerRights;
    }

    public Customer() {
    }

    public Customer(String name, String nachname, int age) {
        this.name = name;
        this.nachname = nachname;
        this.age = age;
    }

    public Customer(String name, String nachname, int age, CustomerRight customerRights) {
        this.name = name;
        this.nachname = nachname;
        this.age = age;
        this.customerRights = customerRights;
    }

    public Customer(int customerid, String name, String nachname, int age, CustomerRight customerRights) {
        this.customerid = customerid;
        this.name = name;
        this.nachname = nachname;
        this.age = age;
        this.customerRights = customerRights;
    }

    public int getId() {
        return customerid;
    }

    public void setId(int customerid) {
        this.customerid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
