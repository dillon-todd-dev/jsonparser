package com.dillontodd.jsonparser.parser;

import com.dillontodd.jsonparser.lexer.Token;
import com.dillontodd.jsonparser.lexer.TokenType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    @Test
    public void basicParserTest() throws Exception {
        // setup
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.OPEN_BRACE, "{"));
        tokens.add(new Token(TokenType.CLOSE_BRACE, "}"));
        Parser parser = new Parser(tokens);

        ASTNode result = parser.parse();

        assert result.equals("valid json");
    }

    @Test
    public void parserStringValue() throws Exception {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.OPEN_BRACE, "{"));
        tokens.add(new Token(TokenType.STRING, "test"));
        tokens.add(new Token(TokenType.COLON, ":"));
        tokens.add(new Token(TokenType.STRING, "test"));
        tokens.add(new Token(TokenType.CLOSE_BRACE, "}"));
        Parser parser = new Parser(tokens);

        ASTNode result = parser.parse();
        assert result != null;
    }
}
