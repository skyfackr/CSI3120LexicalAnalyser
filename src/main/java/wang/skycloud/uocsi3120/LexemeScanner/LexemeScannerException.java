package wang.skycloud.uocsi3120.LexemeScanner;

/**
 * the exception thrown by the LexemeScanner.
 */
public class LexemeScannerException extends RuntimeException{
    @SuppressWarnings("FieldCanBeLocal")
    private final ILexeme lexeme;
    LexemeScannerException(String message,ILexeme lexeme){
        super(message);
        this.lexeme = lexeme;
    }
    void throwStringNotClose(ILexeme lexeme){
        throw new LexemeScannerException("Error - unclosed string literal" , lexeme);
    }
    void throwCommentNotClose(ILexeme lexeme){
        throw new LexemeScannerException("Error - unclosed comment literal" , lexeme);
    }

}
