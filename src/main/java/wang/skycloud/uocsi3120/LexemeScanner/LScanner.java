package wang.skycloud.uocsi3120.LexemeScanner;

public interface LScanner {
    ILexeme getNext();
    ILexeme getNextWithComment();
    }
}
