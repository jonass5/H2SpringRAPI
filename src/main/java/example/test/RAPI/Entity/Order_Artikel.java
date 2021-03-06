package example.test.RAPI.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import example.test.RAPI.Entity.IDClass.Order_ArtikelIdClass;
import example.test.RAPI.JsonDeserializer.Order_ArtikelJsonDeserializer;
import example.test.RAPI.JsonSerializer.Order_ArtikelJsonSerializer;

import javax.persistence.*;

@Entity
@IdClass(Order_ArtikelIdClass.class)
@Table(name = "Order_Artikel")
@JsonSerialize(using = Order_ArtikelJsonSerializer.class)
@JsonDeserialize(using = Order_ArtikelJsonDeserializer.class)
public class Order_Artikel {

    @Id
    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order orderid;

    @Id
    @ManyToOne
    @JoinColumn(name = "artikelid")
    private Artikel artikelid;

    @Column(name = "MENGE")
    private int menge;

    public Order_Artikel(Order order, Artikel artikel, int menge) {
        this.orderid = order;
        this.artikelid = artikel;
        this.menge = menge;
    }

    public Artikel getArtikelid() {
        return artikelid;
    }

    public void setArtikelid(Artikel artikelid) {
        this.artikelid = artikelid;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Order_Artikel() {
    }

    public Order_Artikel(Order order) {
        this.orderid = order;
    }

    public Order getOrderid() {
        return orderid;
    }

    public void setOrderid(Order orderid) {
        this.orderid = orderid;
    }

}
