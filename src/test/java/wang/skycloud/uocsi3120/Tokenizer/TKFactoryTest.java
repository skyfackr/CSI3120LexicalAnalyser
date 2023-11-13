package wang.skycloud.uocsi3120.Tokenizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wang.skycloud.uocsi3120.LexemeScanner.ILexeme;
import wang.skycloud.uocsi3120.LexemeScanner.LScanner;
import wang.skycloud.uocsi3120.LexemeScanner.LScannerFactory;
import wang.skycloud.uocsi3120.LexemeScanner.LexemeType;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TKFactoryTest {

    private static final String content1="1 a\n" +
            "  ;  2 b\n" +
            "  333;33     c     \n" +
            "4%&*(;\n" +
            "//    555555555\n" +
            "6abcde^&*\n"+
            "/*commentjladskjdlksadjlkas\n"+
            "7abcde^&comment*/\n"+
            "\"good\"";

    private static final String content2="// Test Case\n" +
            "if(number >= 3) {\n" +
            "    choice = 1.5;\n" +
            "    for(String statement in file){\n" +
            "        end = \";\";\n" +
            "    }\n" +
            "    while(function_exists()){\n" +
            "        apply_encapsulation_rules();\n" +
            "    }\n" +
            "}\n";
    List<ILexeme> lexemes=new ArrayList<>();

    @BeforeEach
    void setUp() {
        LScanner tester = LScannerFactory.createScanner((new BufferedReader(new StringReader(content2))));

        ILexeme lexeme;
        do {
            lexeme= tester.getNext();
            lexemes.add(lexeme);
            System.out.println(lexeme);
        }while (lexeme.getLexemeType()!= LexemeType.EOF);
        System.out.println("*******************************************");
    }
    @Test
    void tokenize() {
        Iterable<IToken> tokens=TKFactory.tokenize(lexemes);
        for (IToken token:tokens)
            System.out.println(token);
    }
}