package com.dillontodd.jsonparser.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private String input;
    private int currentPosition;

    public Lexer(String input) {
        this.input = input;
        this.currentPosition = 0;
    }

    public List<Token> generateTokens() {
        List<Token> tokens = new ArrayList<>();
        while (currentPosition < this.input.length()) {
            char character = this.input.charAt(currentPosition);
            switch (character) {
                case '{':
                    tokens.add(new Token(TokenType.OPEN_BRACE, String.valueOf(character)));
                    break;
                case '}':
                    tokens.add(new Token(TokenType.CLOSE_BRACE, String.valueOf(character)));
                    break;
                default:
                    break;
            }
            currentPosition++;
        }
        return tokens;
    }
}
