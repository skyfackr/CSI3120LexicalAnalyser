package wang.skycloud.uocsi3120.Tokenizer;

import wang.skycloud.uocsi3120.LexemeScanner.ILexeme;

/**
 * generate comment eof string semicolon operator
 */
public class GeneralTokenizer implements ITokenizer{
    @Override
    public IToken tokenize(ILexeme lexeme) {
        switch (lexeme.getLexemeType())
        {
            case commentBlock:
            case commentLine:
                return new GeneralToken(TokenType.COMMENT,lexeme.getContent(),lexeme.getLine());
            case EOF:
                return new GeneralToken(TokenType.EOF,lexeme.getContent(),lexeme.getLine());

            case String:
                return new GeneralToken(TokenType.STRING,lexeme.getContent(),lexeme.getLine());

            case semicolon:
                return new GeneralToken(TokenType.SEMICOLON,lexeme.getContent(),lexeme.getLine());

            case operator:
                return new GeneralToken(TokenType.OPERATOR,lexeme.getContent(),lexeme.getLine());

        }
        throw new RuntimeException("unreachable");
    }
}
