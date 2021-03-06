package com.common.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EnumSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object anEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (anEnum instanceof Enum) {
            jsonGenerator.writeString(((Enum) anEnum).ordinal() + "");
        } else {
            jsonGenerator.writeString("");
        }
    }
}
