package com.common.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CollectionNullSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (collection == null) {
            jsonGenerator.writeStartArray();
            jsonGenerator.writeEndArray();
        }
    }
}
