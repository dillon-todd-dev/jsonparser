package com.dillontodd.jsonparser;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class JsonIOTest {

    @Test
    public void basicTest() {
        Assertions.assertTrue(JsonIO.parseJson("{}"));
        Assertions.assertFalse(JsonIO.parseJson("{"));
    }
}
