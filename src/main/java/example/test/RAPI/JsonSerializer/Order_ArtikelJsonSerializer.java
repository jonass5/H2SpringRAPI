package example.test.RAPI.JsonSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import example.test.RAPI.Entity.Order_Artikel;

import java.io.IOException;

public class Order_ArtikelJsonSerializer extends StdSerializer<Order_Artikel> {

    public Order_ArtikelJsonSerializer() {
        this(null);
    }

    public Order_ArtikelJsonSerializer(Class<Order_Artikel> t) {
        super(t);
    }

    @Override
    public void serialize(Order_Artikel order_artikel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("OrderID", order_artikel.getOrderid().getOrderid());
        jsonGenerator.writeNumberField("ArtikelID", order_artikel.getArtikelid().getArtikelid());
//        jsonGenerator.writeStringField("Artikelname", order_artikel.getArtikelid().getName());
//        jsonGenerator.writeNumberField("Preis", order_artikel.getArtikelid().getPreis());
        jsonGenerator.writeNumberField("Menge", order_artikel.getMenge());

        jsonGenerator.writeEndObject();
    }
}
