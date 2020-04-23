package example.test.RAPI.JsonDeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import example.test.RAPI.Entity.Customer;
import example.test.RAPI.Entity.CustomerRight;

import java.io.IOException;

public class CustomerJsonDeserialize extends StdDeserializer<Customer> {

    public CustomerJsonDeserialize() {
        this(null);
    }

    public CustomerJsonDeserialize(Class<Customer> vc) {
        super(vc);
    }

    @Override
    public Customer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String name = node.get("Nachname").asText();
        String vorname = node.get("Vorname").asText();
        int age = node.get("Alter").asInt();

        CustomerRight customerRight = null;
        if (!node.get("Rights").isNull()) {
            ObjectNode rightsnode = (ObjectNode) node.get("Rights");

            if (rightsnode.has("RightID")) {
                customerRight = new CustomerRight();
                customerRight.setCustomerrightid(rightsnode.get("RightID").asInt());
            }
        }

        if (node.has("CustomerID")) {
            int customerID = node.get("CustomerID").asInt();
            return new Customer(customerID, vorname, name, age, customerRight);
        }
        return new Customer(vorname, name, age, customerRight);
    }
}
