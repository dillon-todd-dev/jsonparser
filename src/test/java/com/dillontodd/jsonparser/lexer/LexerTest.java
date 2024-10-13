package com.dillontodd.jsonparser.lexer;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

public class LexerTest {

    @Test
    public void emptyJson() {
        String input = "{}";
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.generateTokens();

        assert tokens.size() == 2;
        assert tokens.getFirst().getType() == TokenType.OPEN_BRACE;
        assert tokens.getLast().getType() == TokenType.CLOSE_BRACE;
    }

    @Test
    public void basicJson() {
        String input = "{\"test\": \"test\"}";
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.generateTokens();

        assert tokens.size() == 5;
        assert tokens.getFirst().getType() == TokenType.OPEN_BRACE;
        assert tokens.get(1).getType() == TokenType.STRING;
        assert tokens.get(2).getType() == TokenType.COLON;
        assert tokens.get(3).getType() == TokenType.STRING;
        assert tokens.getLast().getType() == TokenType.CLOSE_BRACE;
    }
}
