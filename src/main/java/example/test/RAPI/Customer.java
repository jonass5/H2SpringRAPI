package example.test.RAPI;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NACHNAME")
    private String nachname;
    @Column(name = "AGE")
    private int age;

    public Customer() {
    }

    public Customer(String name, String nachname, int age) {
        this.name = name;
        this.nachname = nachname;
        this.age = age;
    }

    public Customer(int id, String name, String nachname, int age) {
        this.id = id;
        this.name = name;
        this.nachname = nachname;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
