package wang.skycloud.uocsi3120.Tokenizer;

public class SeparatorToken extends GeneralToken{
    private final Separator separator;
    private final Side side;

    public SeparatorToken(TokenType type, String content, int line, Separator separator, Side side) {
        super(type, content, line);
        this.separator = separator;
        this.side = side;
    }

    public Separator getSeparator() {
        return separator;
    }

    public Side getSide() {
        return side;
    }
    @Override
    public String toString() {
        return String.format("TOKEN{Type=%s, Line=%d, Content=%s, Separator=%s.%s}", getType(), getLine(),getContent(), separator, side);
    }
}
