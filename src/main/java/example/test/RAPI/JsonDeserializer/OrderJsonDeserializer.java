package example.test.RAPI.JsonDeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import example.test.RAPI.Entity.Artikel;
import example.test.RAPI.Entity.Customer;
import example.test.RAPI.Entity.Order;
import example.test.RAPI.Entity.Order_Artikel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class OrderJsonDeserializer extends StdDeserializer<Order> {

    public OrderJsonDeserializer() {
        this(null);
    }

    public OrderJsonDeserializer(Class<Order> vc) {
        super(vc);
    }

    @Override
    public Order deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        ObjectNode customernode = (ObjectNode) node.get("Customer");
        ArrayNode artikellistnode = (ArrayNode) node.get("Artikellist");

        Customer customer = new Customer(customernode.get("CustomerID").asInt());

        ArrayList<Order_Artikel> order_artikellist = new ArrayList<>();
        if (artikellistnode != null) {
            for (int i = 0; i < artikellistnode.size(); i++) {

                JsonNode order_artikelnode = artikellistnode.get(i);
                Order_Artikel order_artikel = new Order_Artikel();

                Order order = new Order();
                int orderid = order_artikelnode.get("OrderID").asInt();

                if (orderid != 0)
                    order.setOrderid(orderid);

                Artikel artikel = new Artikel();
                artikel.setArtikelid(order_artikelnode.get("ArtikelID").asInt());

                order_artikel.setMenge(order_artikelnode.get("Menge").asInt());

                order_artikellist.add(order_artikel);
            }
        }

        if (node.has("OrderID")) {
            int orderid = node.get("OrderID").asInt();
            return new Order(orderid, customer, new HashSet(order_artikellist));
        }
        return new Order(customer);
    }
}
