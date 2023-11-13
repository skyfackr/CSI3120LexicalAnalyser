package wang.skycloud.uocsi3120.Tokenizer;

import wang.skycloud.uocsi3120.LexemeScanner.ILexeme;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LiteralTokenizer implements ITokenizer{

    @Override
    public IToken tokenize(ILexeme lexemes) {
        if (Arrays.stream(keywords).anyMatch(s -> s.equals(lexemes.getContent())))
            return new GeneralToken(TokenType.KEY, lexemes.getContent(), lexemes.getLine());
        if (isInt(lexemes.getContent()))
            return new GeneralToken(TokenType.INT, lexemes.getContent(), lexemes.getLine());
        if (isFloat(lexemes.getContent()))
            return new GeneralToken(TokenType.FLOAT, lexemes.getContent(), lexemes.getLine());
        return new GeneralToken(TokenType.IDENT, lexemes.getContent(), lexemes.getLine());
    }
    //private Set<String> identifierSet=new HashSet<>();
    private static final String[] keywords = new String[]{
            "if", "else", "while", "for", "break", "continue", "return", "int", "float", "void", "String", "bool", "true", "false","in"
    };
    private boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isInt(String s)
    {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
