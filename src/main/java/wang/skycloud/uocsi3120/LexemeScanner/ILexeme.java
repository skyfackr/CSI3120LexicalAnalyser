package wang.skycloud.uocsi3120.LexemeScanner;


/**
 * Interface for lexeme unit
 */
public interface ILexeme {
    /**
     * return all text of the lexeme. Include sign of start and end if this is {@link LexemeType#commentLine}/{@link LexemeType#commentBlock}/{@link LexemeType#String}
     *
     * @return text
     */
    public String getContent();

    /**
     * @return which line of this lexeme in file
     */
    public int getLine();

    /**
     * @return total length of text
     */
    public int getLength();

    /**
     * @return type of this lexeme
     */
    public LexemeType getLexemeType();

}
