package wang.skycloud.uocsi3120.LexemeScanner;

public interface LScanner {
    /**
     * get next lexeme unit. will automatically skip {@link LexemeType#commentLine} and {@link LexemeType#commentBlock}
     *
     * @return next lexeme unit but not comment
     */
    ILexeme getNext();

    /**
     * get next lexeme unit. Will return {@link LexemeType#commentLine} and {@link LexemeType#commentBlock}  normally.
     *
     * @return next lexeme unit
     */
    ILexeme getNextWithComment();

    /**
     * @return current lexeme unit(aka last returned by {@link #getNext()} or {@link #getNextWithComment()})
     */
    ILexeme getCurrent();

}
