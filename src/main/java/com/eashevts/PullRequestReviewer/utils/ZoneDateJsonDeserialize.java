package com.eashevts.PullRequestReviewer.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.format.datetime.DateFormatter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZoneDateJsonDeserialize extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String valueAsString = p.getValueAsString();
        ZonedDateTime zonedDate = null;
        if (valueAsString != null) {
            return ZonedDateTime.parse(valueAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
        }
        return null;
    }
}
