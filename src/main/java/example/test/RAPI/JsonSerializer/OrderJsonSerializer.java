package example.test.RAPI.JsonSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import example.test.RAPI.Entity.Order;

import java.io.IOException;

public class OrderJsonSerializer extends StdSerializer<Order> {

    public OrderJsonSerializer() {
        this(null);
    }

    public OrderJsonSerializer(Class<Order> t) {
        super(t);
    }

    @Override
    public void serialize(Order order, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("OrderID", order.getOrderid());

        jsonGenerator.writeObjectFieldStart("Customer");
        jsonGenerator.writeObjectField("Vorname", order.getCustomer().getName());
        jsonGenerator.writeObjectField("Nachname", order.getCustomer().getNachname());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectField("Artikellist", order.getArtikelList());

        jsonGenerator.writeEndObject();
    }
}