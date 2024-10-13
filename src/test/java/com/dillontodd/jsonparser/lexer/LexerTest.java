package com.dillontodd.jsonparser.lexer;

import org.junit.Test;

import java.util.List;

public class LexerTest {

    @Test
    public void basicJsonTest() {
        String input = "{}";
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.generateTokens();

        assert tokens.size() == 2;
        assert tokens.getFirst().getType() == TokenType.OPEN_BRACE;
        assert tokens.getLast().getType() == TokenType.CLOSE_BRACE;
    }
}
