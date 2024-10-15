package com.dillontodd.jsonparser;

import com.dillontodd.jsonparser.lexer.Lexer;
import com.dillontodd.jsonparser.lexer.Token;
import com.dillontodd.jsonparser.parser.ASTNode;
import com.dillontodd.jsonparser.parser.Parser;

import java.util.List;

public class JsonIO {

    public static ASTNode parseJson(String input) {
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.generateTokens();
        Parser parser = new Parser(tokens);

        ASTNode node = null;
        try {
            node = parser.parse();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return node;
    }
}
