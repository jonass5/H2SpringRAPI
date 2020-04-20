package example.test.RAPI.JsonSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import example.test.RAPI.Entity.Customer;

import java.io.IOException;

public class CustomerJsonSerializer extends StdSerializer<Customer> {

    public CustomerJsonSerializer() {
        this(null);
    }

    public CustomerJsonSerializer(Class<Customer> t) {
        super(t);
    }

    @Override
    public void serialize(Customer customer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("CustomerID", customer.getId());

        jsonGenerator.writeStringField("Nachname", customer.getNachname());
        jsonGenerator.writeStringField("Vorname", customer.getName());
        jsonGenerator.writeNumberField("Alter", customer.getAge());

        jsonGenerator.writeObjectField("Rights", customer.getCustomerRights());

//        jsonGenerator.writeObjectField("Orders: ", customer.getOrderList());

        jsonGenerator.writeEndObject();
    }

}
