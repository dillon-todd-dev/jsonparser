package com.dillontodd.jsonparser;

import com.dillontodd.jsonparser.parser.ASTNode;
import org.junit.Test;

public class JsonIOTest {

    @Test
    public void basicTest() {
        ASTNode validJson = JsonIO.parseJson("{}");
        ASTNode invalidJson = JsonIO.parseJson("{");

        assert validJson.equals("valid json");
        assert invalidJson.equals("invalid json");
    }
}
