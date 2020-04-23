package example.test.RAPI.JsonSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import example.test.RAPI.Entity.CustomerRight;

import java.io.IOException;

public class CustomerRightsJsonSerializer extends StdSerializer<CustomerRight> {

    public CustomerRightsJsonSerializer() {
        this(null);
    }

    public CustomerRightsJsonSerializer(Class<CustomerRight> t) {
        super(t);
    }

    @Override
    public void serialize(CustomerRight customerRights, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("RightID", customerRights.getCustomerrightid());
        jsonGenerator.writeStringField("Name", customerRights.getName());

        jsonGenerator.writeEndObject();
    }

}