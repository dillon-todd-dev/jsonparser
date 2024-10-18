package com.dillontodd.jsonparser;

import java.io.InputStream;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class JsonIOTest {

    @Test
    public void step1Test() throws Exception {
        InputStream validStream = getClass().getResourceAsStream("/tests/step1/valid.json");
        InputStream invalidStream = getClass().getResourceAsStream("/tests/step1/invalid.json");
        String validJson = new String(validStream.readAllBytes());
        String invalidJson = new String(invalidStream.readAllBytes());
        validStream.close();
        invalidStream.close();

        Assertions.assertTrue(JsonIO.parseJson(validJson));
        Assertions.assertFalse(JsonIO.parseJson(invalidJson));
    }
}
