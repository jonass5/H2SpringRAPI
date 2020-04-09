package example.test.RAPI.JsonSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import example.test.RAPI.Entity.Artikel;

import java.io.IOException;

public class ArtikelJsonSerializer extends StdSerializer<Artikel> {

    public ArtikelJsonSerializer() {
        this(null);
    }

    public ArtikelJsonSerializer(Class<Artikel> t) {
        super(t);
    }

    @Override
    public void serialize(Artikel artikel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("ArtikelID", artikel.getArtikelid());
        jsonGenerator.writeStringField("Artikelname", artikel.getName());
        jsonGenerator.writeNumberField("Preis", artikel.getPreis());

        jsonGenerator.writeEndObject();
    }
}