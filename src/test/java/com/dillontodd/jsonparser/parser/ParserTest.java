package com.dillontodd.jsonparser.parser;

import com.dillontodd.jsonparser.exceptions.JSONException;
import com.dillontodd.jsonparser.lexer.Token;
import com.dillontodd.jsonparser.lexer.TokenType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    @Test
    public void basicParserTest() throws JSONException {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.OPEN_BRACE, "{"));
        tokens.add(new Token(TokenType.CLOSE_BRACE, "}"));
        boolean result = new Parser(tokens).parse();

        assert result;
    }

    @Test
    public void parserStringValue() throws JSONException {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.OPEN_BRACE, "{"));
        tokens.add(new Token(TokenType.STRING, "test"));
        tokens.add(new Token(TokenType.COLON, ":"));
        tokens.add(new Token(TokenType.STRING, "test"));
        tokens.add(new Token(TokenType.CLOSE_BRACE, "}"));
        boolean result = new Parser(tokens).parse();

        assert result;
    }
}
