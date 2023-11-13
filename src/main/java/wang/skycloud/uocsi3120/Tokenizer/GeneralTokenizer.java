package wang.skycloud.uocsi3120.Tokenizer;

import wang.skycloud.uocsi3120.LexemeScanner.ILexeme;

import java.util.HashSet;
import java.util.Set;

public class GeneralTokenizer {

    /**
     * receive lexemes in one line. return tokens.
     * when find a {@link wang.skycloud.uocsi3120.LexemeScanner.LexemeType#EOF} this will discard all lexemes left
     *
     * @param lexemes all lexemes in one line
     * @return token list
     */
    public static IToken[] tokenize(ILexeme[] lexemes) {
        identifierSet=new HashSet<>();
        //TODO: tokenize not implemented created at 2023/11/13 12:55
        throw new RuntimeException("tokenize not implemented");
    }
    private static Set<String> identifierSet;
    private static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
