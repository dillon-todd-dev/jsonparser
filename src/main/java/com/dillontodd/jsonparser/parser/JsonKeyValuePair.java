package com.dillontodd.jsonparser.parser;

import java.util.HashMap;

public class JsonKeyValuePair {

    private String key;
    private Object value;

    public JsonKeyValuePair(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
