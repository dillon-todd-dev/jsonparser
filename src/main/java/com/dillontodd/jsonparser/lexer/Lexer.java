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
                case ':':
                    tokens.add(new Token(TokenType.COLON, String.valueOf(character)));
                    break;
                case '"':
                    tokens.add(generateStringToken());
                    break;
                default:
                    break;
            }
            currentPosition++;
        }
        return tokens;
    }

    private Token generateStringToken() {
        StringBuilder stringSequence = new StringBuilder();
        this.currentPosition++;
        while (this.input.charAt(this.currentPosition) != '"') {
            stringSequence.append(this.input.charAt(this.currentPosition));
            this.currentPosition++;
        }
        return new Token(TokenType.STRING, stringSequence.toString());
    }
}
