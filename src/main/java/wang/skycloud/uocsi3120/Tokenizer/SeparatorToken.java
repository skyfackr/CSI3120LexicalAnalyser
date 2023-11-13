package wang.skycloud.uocsi3120.Tokenizer;

public class SeparatorToken extends GeneralToken{
    private final Separator separator;
    private final Side side;

    public SeparatorToken(TokenType type, String content, int line, int length, Separator separator, Side side) {
        super(type, content, line, length);
        this.separator = separator;
        this.side = side;
    }

    public Separator getSeparator() {
        return separator;
    }

    public Side getSide() {
        return side;
    }
}
