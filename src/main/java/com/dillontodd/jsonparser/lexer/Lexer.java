package com.dillontodd.jsonparser.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final String json;
    private int index;

    public Lexer(String json) {
        this.json = json;
        this.index = 0;
    }

    public List<Token> generateTokens() {
        List<Token> tokens = new ArrayList<>();
        while (index < this.json.length()) {
            char character = this.json.charAt(index);

            if (character == '{') {
                tokens.add(new Token(TokenType.OPEN_BRACE, String.valueOf(character)));
            } else if (character == '}') {
                tokens.add(new Token(TokenType.CLOSE_BRACE, String.valueOf(character)));
            } else if (character == ':') {
                tokens.add(new Token(TokenType.COLON, String.valueOf(character)));
            } else if (character == '"') {
                tokens.add(generateStringToken());
            }

            index++;
        }
        return tokens;
    }

    private Token generateStringToken() {
        StringBuilder stringBuilder = new StringBuilder();
        this.index++;
        while (this.json.charAt(this.index) != '"') {
            stringBuilder.append(this.json.charAt(this.index));
            this.index++;
        }
        return new Token(TokenType.STRING, stringBuilder.toString());
    }
}
