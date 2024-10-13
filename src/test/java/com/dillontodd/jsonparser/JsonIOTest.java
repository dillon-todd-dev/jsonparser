package com.dillontodd.jsonparser;

import org.junit.Test;

public class JsonIOTest {

    @Test
    public void basicTest() {
        String validJson = JsonIO.parseJson("{}");
        String invalidJson = JsonIO.parseJson("{");

        assert validJson.equals("valid json");
        assert invalidJson.equals("invalid json");
    }
}
