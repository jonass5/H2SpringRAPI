package example.test.RAPI.Entity.IDClass;

import java.io.Serializable;

public class Order_ArtikelIdClass implements Serializable {
    private int orderid;
    private int artikelid;

    public Order_ArtikelIdClass() {
    }

    public Order_ArtikelIdClass(int orderid, int artikelid) {
        this.orderid = orderid;
        this.artikelid = artikelid;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getArtikelid() {
        return artikelid;
    }

    public void setArtikelid(int artikelid) {
        this.artikelid = artikelid;
    }
}
