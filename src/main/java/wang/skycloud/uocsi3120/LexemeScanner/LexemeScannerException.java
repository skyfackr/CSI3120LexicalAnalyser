package wang.skycloud.uocsi3120.LexemeScanner;

import java.io.IOException;

/**
 * the exception thrown by the LexemeScanner.
 */
public class LexemeScannerException extends RuntimeException{
    @SuppressWarnings("FieldCanBeLocal")
    private final ILexeme lexeme;
    private LexemeScannerException(String message,ILexeme lexeme){
        super(message);
        this.lexeme = lexeme;
    }
    private LexemeScannerException(String message,ILexeme lexeme,Throwable cause){
        super(message,cause);
        this.lexeme = lexeme;
    }
    static void throwStringNotClose(ILexeme lexeme){
        throw new LexemeScannerException("Error - unclosed string literal" , lexeme);
    }
    static void throwCommentNotClose(ILexeme lexeme){
        throw new LexemeScannerException("Error - unclosed comment literal" , lexeme);
    }
    static void throwIOException(IOException e){
        throw new LexemeScannerException("Error - IOException" , null,e);
    }

}
