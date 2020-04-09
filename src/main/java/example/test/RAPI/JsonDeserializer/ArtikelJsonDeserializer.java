package example.test.RAPI.JsonDeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import example.test.RAPI.Entity.Artikel;

import java.io.IOException;

public class ArtikelJsonDeserializer extends StdDeserializer<Artikel> {

    public ArtikelJsonDeserializer() {
        this(null);
    }

    public ArtikelJsonDeserializer(Class<Artikel> vc) {
        super(vc);
    }

    @Override
    public Artikel deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String artikelname = node.get("Artikelname").asText();
        double preis = node.get("Preis").asDouble();

        if (node.has("ArtikelID")) {
            int artikelid = node.get("ArtikelID").asInt();
            return new Artikel(artikelid, artikelname, preis);
        }
        return new Artikel(artikelname, preis);
    }
}
