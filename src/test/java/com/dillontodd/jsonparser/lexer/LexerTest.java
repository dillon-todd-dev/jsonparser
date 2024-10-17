package com.dillontodd.jsonparser.lexer;

import com.dillontodd.jsonparser.exceptions.JSONException;
import org.junit.Test;

import java.util.List;

public class LexerTest {

    @Test
    public void emptyJson() throws JSONException {
        String input = "{}";
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.generateTokens();

        assert tokens.size() == 2;
        assert tokens.getFirst().getType() == TokenType.OPEN_BRACE;
        assert tokens.getLast().getType() == TokenType.CLOSE_BRACE;
    }

    @Test
    public void basicJson() throws JSONException {
        String input = """
        {
          "test": "test",
          "test1": "test1"
        }
        """;
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.generateTokens();

        assert tokens.size() == 19;
        assert tokens.getFirst().getType() == TokenType.OPEN_BRACE;
        assert tokens.get(1).getType() == TokenType.NEW_LINE;
        assert tokens.get(2).getType() == TokenType.WHITESPACE;
        assert tokens.get(3).getType() == TokenType.WHITESPACE;
        assert tokens.get(4).getType() == TokenType.STRING;
        assert tokens.get(5).getType() == TokenType.COLON;
        assert tokens.get(6).getType() == TokenType.WHITESPACE;
        assert tokens.get(7).getType() == TokenType.STRING;
        assert tokens.get(8).getType() == TokenType.COMMA;
        assert tokens.get(9).getType() == TokenType.NEW_LINE;
        assert tokens.get(10).getType() == TokenType.WHITESPACE;
        assert tokens.get(11).getType() == TokenType.WHITESPACE;
        assert tokens.get(12).getType() == TokenType.STRING;
        assert tokens.get(13).getType() == TokenType.COLON;
        assert tokens.get(14).getType() == TokenType.WHITESPACE;
        assert tokens.get(15).getType() == TokenType.STRING;
        assert tokens.get(16).getType() == TokenType.NEW_LINE;
        assert tokens.get(17).getType() == TokenType.CLOSE_BRACE;
        assert tokens.getLast().getType() == TokenType.NEW_LINE;
    }
}
