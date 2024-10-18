package com.dillontodd.jsonparser;

import com.dillontodd.jsonparser.exceptions.JSONException;
import com.dillontodd.jsonparser.lexer.Lexer;
import com.dillontodd.jsonparser.lexer.Token;
import com.dillontodd.jsonparser.parser.Parser;

import java.util.List;

public class JsonIO {

    public static boolean parseJson(String input) {
        boolean valid;
        try {
            List<Token> tokens = new Lexer(input).generateTokens();
            valid = new Parser(tokens).parse();
        } catch (JSONException exception) {
            valid = false;
            exception.printStackTrace();
        }
        return valid;
    }
}
