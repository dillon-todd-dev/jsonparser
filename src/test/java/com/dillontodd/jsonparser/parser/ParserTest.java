package com.dillontodd.jsonparser.parser;

import com.dillontodd.jsonparser.lexer.Token;
import com.dillontodd.jsonparser.lexer.TokenType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    @Test
    public void basicParserTest() {
        // setup
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.OPEN_BRACE, "{"));
        tokens.add(new Token(TokenType.CLOSE_BRACE, "}"));
        Parser parser = new Parser(tokens);

        String result = parser.parse();

        assert result.equals("valid json");
    }
}
