package wang.skycloud.uocsi3120.Tokenizer;

public class GeneralToken implements IToken{
    private final TokenType type;
    private final String content;
    private final int line;
    private final int length;

    public GeneralToken(TokenType type, String content, int line, int length) {
        this.type = type;
        this.content = content;
        this.line = line;
        this.length = length;
    }

    @Override
    public TokenType getType() {
        return type;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getLength() {
        return length;
    }

}
