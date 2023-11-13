package wang.skycloud.uocsi3120.LexemeScanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.AbstractDocument;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class NoviceLScannerTest {
    private static final String content="1 a\n" +
            "  ;  2 b\n" +
            "  333;33     c     \n" +
            "4%&*(;\n" +
            "//    555555555\n" +
            "6abcde^&*\n"+
            "/*commentjladskjdlksadjlkas\n"+
            "7abcde^&comment*/\n"+
            "\"good\"";
    private  LScanner tester;

    @BeforeEach
    void setUp() {
        tester=LScannerFactory.createScanner((new BufferedReader(new StringReader(content))));
    }

    @Test
    void getNext() {
        ILexeme lexeme;
        int countdown=100;
        do {
            lexeme=tester.getNext();
            System.out.println(lexeme);
            countdown--;
            if (countdown<=0) fail("loop time out");
        }while (lexeme.getLexemeType()!=LexemeType.EOF);
    }

    @Test
    void getNextWithComment() {
    }

    @Test
    void getCurrent() {
    }
}