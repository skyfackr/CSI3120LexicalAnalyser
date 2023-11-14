package wang.skycloud.uocsi3120.Tokenizer;

import wang.skycloud.uocsi3120.LexemeScanner.ILexeme;

public class TokenizeError extends RuntimeException{
    private TokenizeError(String message){
        super(message);
    }
    private ILexeme lexeme;
    private TokenizeError(String message,ILexeme lexeme){
        super(message );
        this.lexeme = lexeme;
    }
    static void throwFloatPointError(ILexeme lexeme){
        throw new TokenizeError("tokenization_error - float point error",lexeme);
    }
}
