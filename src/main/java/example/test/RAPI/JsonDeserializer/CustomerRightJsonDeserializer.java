package example.test.RAPI.JsonDeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import example.test.RAPI.Entity.CustomerRight;

import java.io.IOException;

public class CustomerRightJsonDeserializer extends StdDeserializer<CustomerRight> {

    public CustomerRightJsonDeserializer() {
        this(null);
    }

    public CustomerRightJsonDeserializer(Class<CustomerRight> vc) {
        super(vc);
    }

    @Override
    public CustomerRight deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String rightname = node.get("Name").asText();

        if (node.has("RightID")) {
            int rightid = node.get("RightID").asInt();
            return new CustomerRight(rightid, rightname);
        }
        return new CustomerRight(rightname);
    }
}
