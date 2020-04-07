package example.test.RAPI.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int customerid;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NACHNAME")
    private String nachname;

    @Column(name = "AGE")
    private int age;

    @ManyToOne
    @JoinColumn(name = "customerrightid")
    private CustomerRights customerRights;

    @OneToMany(mappedBy = "orderid")
    private Set<Order> orderList;

    @JsonIgnore
    public Set<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<Order> orderList) {
        this.orderList = orderList;
    }

    public CustomerRights getCustomerRights() {
        return customerRights;
    }

    public void setCustomerRights(CustomerRights customerRights) {
        this.customerRights = customerRights;
    }

    public Customer() {
    }

    public Customer(String name, String nachname, int age) {
        this.name = name;
        this.nachname = nachname;
        this.age = age;
    }

    public Customer(int id, String name, String nachname, int age, CustomerRights customerRights) {
        this.customerid = id;
        this.name = name;
        this.nachname = nachname;
        this.age = age;
        this.customerRights = customerRights;
    }

    public int getId() {
        return customerid;
    }

    public void setId(int id) {
        this.customerid = id;
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
