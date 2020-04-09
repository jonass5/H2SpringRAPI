package example.test.RAPI.JsonDeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import example.test.RAPI.Entity.Artikel;
import example.test.RAPI.Entity.Order;
import example.test.RAPI.Entity.Order_Artikel;

import java.io.IOException;

public class Order_ArtikelJsonDeserializer extends StdDeserializer<Order_Artikel> {

    public Order_ArtikelJsonDeserializer() {
        this(null);
    }

    public Order_ArtikelJsonDeserializer(Class<Order_Artikel> vc) {
        super(vc);
    }

    @Override
    public Order_Artikel deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        int orderid = node.get("OrderID").asInt();
        int artikelid = node.get("ArtikelID").asInt();

        int menge = node.get("Menge").asInt();

        return new Order_Artikel(new Order(orderid), new Artikel(artikelid), menge);
    }
}
