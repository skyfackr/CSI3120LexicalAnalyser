package wang.skycloud.uocsi3120.LexemeScanner;

import javax.swing.text.AbstractDocument;

public class LexemeUnit implements ILexeme{
    private final String content;
    private final int line;
    private final LexemeType LexemeType;

    LexemeUnit(String content, int line, LexemeType LexemeType) {
        this.content = content;
        this.line = line;
        this.LexemeType = LexemeType;
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
    @Override
    public LexemeType getLexemeType() {
        return LexemeType;
    }
    @Override
    public String toString() {
        return String.format("NoviceLScanner{line=%d, type=%s, content=%s}", line, LexemeType, content);
    }
}
