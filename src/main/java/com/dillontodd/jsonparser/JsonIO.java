package com.dillontodd.jsonparser;

import com.dillontodd.jsonparser.lexer.Lexer;
import com.dillontodd.jsonparser.lexer.Token;
import com.dillontodd.jsonparser.parser.Parser;

import java.util.List;

public class JsonIO {

    public static String parseJson(String input) {
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.generateTokens();

        Parser parser = new Parser(tokens);
        return parser.parse();
    }
}
