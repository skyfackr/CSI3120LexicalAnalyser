package wang.skycloud.uocsi3120.Tokenizer;

import wang.skycloud.uocsi3120.LexemeScanner.ILexeme;
import wang.skycloud.uocsi3120.LexemeScanner.LScannerFactory;
import wang.skycloud.uocsi3120.LexemeScanner.LexemeType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TKFactory {
    /**
     * auto choose suitable tokenizer to tokenize the lexeme. will detect the float lexemes and combine them to one token
     *
     * @param lexeme lexeme to be tokenized
     * @return tokenized token
     */
    public static Iterable<IToken> tokenize(Iterable<ILexeme> lexeme) {
        //TODO: tokenize not implemented created at 2023/11/13 1:34
        throw new RuntimeException("tokenize not implemented");
    }
    private static ILexeme combineFloatLexeme(ILexeme first,ILexeme point, ILexeme last) {
        if (!(first.getContent().chars().allMatch(Character::isDigit) && last.getContent().chars().allMatch(Character::isDigit)&&first.getLine()==last.getLine()))
            TokenizeError.throwFloatPointError(point);
        return LScannerFactory.createLexemeUnit(first.getContent()+point.getContent()+last.getContent(),first.getLine(), LexemeType.literal);
    }

    /**
     * find float point and combine them to one token. return new lexeme list
     *
     * @param lexemes original lexeme list
     * @return list with float literal and without float point/first and last number
     */
    private static Iterable<ILexeme> findFloatLexeme(Iterable<ILexeme> lexemes){
        ArrayList<ILexeme> newList=new ArrayList<>();
        ILexeme now;
        Iterator<ILexeme> iterator=lexemes.iterator();
        while(iterator.hasNext())
        {
            now=iterator.next();
            if (now.getLexemeType()==LexemeType.literal)
            {
                if (now.getContent().contentEquals("."))
                {
                    if (newList.isEmpty()||(!iterator.hasNext()))
                        TokenizeError.throwFloatPointError(now);
                    newList.set(newList.size()-1,combineFloatLexeme(newList.get(newList.size()-1),now,iterator.next()));
                    continue;
                }
            }
            newList.add(now);
        }
        return newList;
    }
}