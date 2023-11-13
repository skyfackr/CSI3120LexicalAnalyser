package wang.skycloud.uocsi3120.Tokenizer;

import wang.skycloud.uocsi3120.LexemeScanner.ILexeme;

public class SeparatorTokenizer implements ITokenizer{

    @Override
    public  IToken tokenize(ILexeme lexeme) {
        switch(lexeme.getContent())
        {
            case "(":
                return new SeparatorToken(TokenType.SEPARATOR, lexeme.getContent(), lexeme.getLine(), Separator.Parentheses, Side.Left);
            case ")":
                return new SeparatorToken(TokenType.SEPARATOR, lexeme.getContent(), lexeme.getLine(), Separator.Parentheses, Side.Right);
            case "[":
                return new SeparatorToken(TokenType.SEPARATOR, lexeme.getContent(), lexeme.getLine(), Separator.Brace, Side.Left);
            case "]":
                return new SeparatorToken(TokenType.SEPARATOR, lexeme.getContent(), lexeme.getLine(), Separator.Brace, Side.Right);
            case "{":
                return new SeparatorToken(TokenType.SEPARATOR, lexeme.getContent(), lexeme.getLine(), Separator.Bracket, Side.Left);
            case "}":
                return new SeparatorToken(TokenType.SEPARATOR, lexeme.getContent(), lexeme.getLine(), Separator.Bracket, Side.Right);
        }
        throw new RuntimeException("unreachable");
    }
}
