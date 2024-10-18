package com.dillontodd.jsonparser;

import java.io.InputStream;
import java.util.Objects;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class JsonIOTest {

    @Test
    public void step1Test() throws Exception {
        InputStream validStream = getClass().getResourceAsStream("/tests/step1/valid.json");
        InputStream invalidStream = getClass().getResourceAsStream("/tests/step1/invalid.json");
        String validJson = new String(Objects.requireNonNull(validStream).readAllBytes());
        String invalidJson = new String(Objects.requireNonNull(invalidStream).readAllBytes());
        validStream.close();
        invalidStream.close();

        Assertions.assertTrue(JsonIO.parseJson(validJson));
        Assertions.assertFalse(JsonIO.parseJson(invalidJson));
    }

    @Test
    public void step2Test() throws Exception {
        InputStream validStream = getClass().getResourceAsStream("/tests/step2/valid.json");
        InputStream valid2Stream = getClass().getResourceAsStream("/tests/step2/valid2.json");
        InputStream invalidStream = getClass().getResourceAsStream("/tests/step2/invalid.json");
        InputStream invalid2Stream = getClass().getResourceAsStream("/tests/step2/invalid2.json");
        String validString = new String(Objects.requireNonNull(validStream).readAllBytes());
        String valid2String = new String(Objects.requireNonNull(valid2Stream).readAllBytes());
        String invalidString = new String(Objects.requireNonNull(invalidStream).readAllBytes());
        String invalid2String = new String(Objects.requireNonNull(invalid2Stream).readAllBytes());
        validStream.close();
        valid2Stream.close();
        invalidStream.close();
        invalid2Stream.close();

        Assertions.assertTrue(JsonIO.parseJson(validString));
        Assertions.assertTrue(JsonIO.parseJson(valid2String));
        Assertions.assertFalse(JsonIO.parseJson(invalidString));
        Assertions.assertFalse(JsonIO.parseJson(invalid2String));
    }
}
