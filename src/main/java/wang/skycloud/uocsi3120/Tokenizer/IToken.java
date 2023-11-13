package wang.skycloud.uocsi3120.Tokenizer;

/**
 * interface of basic token
 */
public interface IToken {
    /**
     * return type of token. may use this to determine the class of token
     *
     * @return type
     */
    TokenType getType();

    /**
     * @return content of token
     */
    String getContent();

    /**
     * @return which line of this token in file
     */
    int getLine();

    /**
     * @return total length of content
     */
    int getLength();
}
