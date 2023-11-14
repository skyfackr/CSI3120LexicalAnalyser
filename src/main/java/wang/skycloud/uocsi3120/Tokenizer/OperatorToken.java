package wang.skycloud.uocsi3120.Tokenizer;

import java.util.Arrays;

public class OperatorToken extends GeneralToken{
    private final OperatorType type;
    public OperatorToken(TokenType type, String content, int line) {
        super(type, content, line);
        this.type=analyseType();
    }

    private static final String[] ComparatorOperators={"<",">","<=",">=","==","!="};
    private OperatorType analyseType()
    {
        if ("+-*/".chars().anyMatch(c -> c == getContent().charAt(0))&&getContent().length()==1)
            return OperatorType.Algorithm;
        if (Arrays.stream(ComparatorOperators).anyMatch(c -> c .equals(getContent())))
            return OperatorType.Comparator;
        if (getContent().equals("="))
            return OperatorType.Assign;
        return OperatorType.Other;
    }
    public OperatorType getOperatorType()
    {
        return type;
    }

    @Override
    public String toString() {
        return String.format("TOKEN{Type=%s, Line=%d, Content=%s, Operator=%s}", getType(), getLine(),getContent(),getOperatorType());
    }
}
