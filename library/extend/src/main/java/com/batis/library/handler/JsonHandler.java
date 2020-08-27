package com.batis.library.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonHandler<T> {
    private final static Map<String, ObjectMapper> MAPPER_MAP;

    static {
        MAPPER_MAP = new HashMap<>();
        MAPPER_MAP.put("default", new ObjectMapper());
        MAPPER_MAP.put("ignores", new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
    }

    private  ObjectMapper getDefaultMapper(){
        return MAPPER_MAP.get("default");
    }

    private void setDefaultMapper(ObjectMapper mapper){
        MAPPER_MAP.put("default", mapper);
    }

    public JsonHandler() {
    }

    public JsonHandler(ObjectMapper replaceDefault) {
        setDefaultMapper(replaceDefault);
    }

    public JsonHandler<T> parseJson(String json, T t) {
        return this;
    }

    public JsonHandler<T> genericParse(String json) throws JsonProcessingException {
        JsonNode jsonNode = getDefaultMapper().readValue(json,JsonNode.class);
        return this;
    }

}
