package wang.skycloud.uocsi3120.Tokenizer;

public class GeneralToken extends ITokenWithToString{
    private final TokenType type;
    private final String content;
    private final int line;

    public GeneralToken(TokenType type, String content, int line) {
        this.type = type;
        this.content = content;
        this.line = line;
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
        return content.length();
    }

}
