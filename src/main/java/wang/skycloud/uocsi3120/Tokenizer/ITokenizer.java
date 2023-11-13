package wang.skycloud.uocsi3120.Tokenizer;

import wang.skycloud.uocsi3120.LexemeScanner.ILexeme;

/**
 * interface of all tokenizers
 */
@Deprecated
public interface ITokenizer {
    /**
     * use this to tokenize a lexeme. must make sure the type is right
     *
     * @param lexeme lexeme to be tokenized
     * @return tokenized token
     */
    IToken tokenize(ILexeme lexeme);
}
