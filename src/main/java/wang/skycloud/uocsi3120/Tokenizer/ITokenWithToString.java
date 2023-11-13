package wang.skycloud.uocsi3120.Tokenizer;

public abstract class ITokenWithToString implements IToken{
    @Override
    public String toString() {
        return String.format("TOKEN{Type=%s, Line=%d, Content=%s}", getType(), getLine(),getContent());
    }
}
