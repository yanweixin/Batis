package com.batis.library.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonHandler<T> {
    private final static Map<String, ObjectMapper> MAPPER_MAP;

    static {
        MAPPER_MAP = new HashMap<>();
        MAPPER_MAP.put("default", new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
    }

    private ObjectMapper getDefaultMapper() {
        return MAPPER_MAP.get("default");
    }

    private void setDefaultMapper(ObjectMapper mapper) {
        MAPPER_MAP.put("default", mapper);
    }

    public JsonHandler() {
    }

    public JsonHandler(ObjectMapper replaceDefault) {
        setDefaultMapper(replaceDefault);
    }

    public JsonHandler<T> parseJson(String pathname) {

        return this;
    }

    public JsonHandler<T> genericParse(String pathName) throws IOException {
        ObjectMapper mapper = MAPPER_MAP.get("default");
        String json = Files.readString(Paths.get(pathName));
        JsonNode jsonNode = mapper.readTree(json);
        return this;
    }

}
