package wang.skycloud.uocsi3120.LexemeScanner;

import java.io.BufferedReader;
import java.io.IOException;

public class NoviceLScanner implements LScanner{
    private final BufferedReader reader;
    private String currentLine;
    private int lineCount=1;

    NoviceLScanner(BufferedReader reader) {
        this.reader = reader;
    }
    private void truncLine(int start)
    {
        currentLine=currentLine.substring(start);
    }

    /**
     * read a total string when find a Quotation. Return the string until the next Quotation.
     *
     * @return a {@link ILexeme} with type {@link LexemeType#String}
     */
    private ILexeme readString(){
        StringBuilder sb = new StringBuilder();
        sb.append(currentLine.charAt(0));
        truncLine(1);
        int nextQuotation=-1;
        while ((nextQuotation = currentLine.indexOf('\"')) == -1) {
            sb.append(currentLine);
            sb.append(System.lineSeparator());
            readNextLine();
            if (currentLine.isEmpty()) {
                LexemeScannerException.throwStringNotClose(new LexemeUnit(sb.toString(), lineCount, LexemeType.String));
            }
        }
        sb.append(currentLine, 0, nextQuotation+1);
        truncLine(nextQuotation+1);
        return LScannerFactory.createLexemeUnit(sb.toString(), lineCount, LexemeType.String);
    }

    /**
     * read a total string when find a commentBlockTag. Return the string until the next commentBlockTag.
     *
     * @return a {@link ILexeme} with type {@link LexemeType#commentBlock}
     */
    private ILexeme readCommentBlock()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(currentLine.charAt(0));
        truncLine(1);
        int nextCommentSign=-1;
        while ((nextCommentSign = currentLine.indexOf("*/")) == -1) {
            sb.append(currentLine);
            sb.append(System.lineSeparator());
            readNextLine();
            if (currentLine.isEmpty()) {
                LexemeScannerException.throwCommentNotClose(new LexemeUnit(sb.toString(), lineCount, LexemeType.commentBlock));
            }
        }
        sb.append(currentLine, 0, nextCommentSign+2);
        truncLine(nextCommentSign+2);
        return LScannerFactory.createLexemeUnit(sb.toString(), lineCount, LexemeType.commentBlock);
    }

    /**
     * Read next lexeme from current line. When a lexeme is read, the current line will be cut off.
     *
     * @return
     */
    private ILexeme readLexeme(){
        updateLine();
        if (currentLine.isEmpty()) {
            return LScannerFactory.createLexemeUnit("", lineCount, LexemeType.EOF);
        }
        char firstChar = currentLine.charAt(0);
        if (firstChar == '\"') {
            return readString();
        }
        if (firstChar == '/') {
            if (currentLine.charAt(1) == '/') {
                return LScannerFactory.createLexemeUnit(currentLine, lineCount, LexemeType.commentLine);
            }
            if (currentLine.charAt(1) == '*') {
                return readCommentBlock();
            }
        }
        charType currentType=getCharType(firstChar);
        if (currentLine.length()==1){
            truncLine(1);
            return (currentType==charType.other)?LScannerFactory.createLexemeUnit(String.valueOf(firstChar), lineCount, LexemeType.operator):LScannerFactory.createLexemeUnit(String.valueOf(firstChar), lineCount, LexemeType.literal);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(firstChar);
        int index=1;
        while(currentLine.charAt(index)!=' '&&getCharType(currentLine.charAt(index))!=currentType)
        {
            sb.append(currentLine.charAt(index));
            index++;
            if(index==currentLine.length())
            {
                truncLine(index);
                return (currentType==charType.other)?LScannerFactory.createLexemeUnit(sb.toString(), lineCount, LexemeType.operator):LScannerFactory.createLexemeUnit(sb.toString(), lineCount, LexemeType.literal);
            }
        }
        truncLine(index);
        return (currentType==charType.other)?LScannerFactory.createLexemeUnit(sb.toString(), lineCount, LexemeType.operator):LScannerFactory.createLexemeUnit(sb.toString(), lineCount, LexemeType.literal);
    }
    enum charType{
        letter,
        digit,
        other
    }
    private charType getCharType(char c)
    {
        if(Character.isLetter(c))
        {
            return charType.letter;
        }
        if(Character.isDigit(c))
        {
            return charType.digit;
        }
        return charType.other;
    }

    /**
     * update current line with next line. previous data will be erased. Will trim line and skip empty line. Return empty only if EOF.
     *
     * @return updated current line
     */
    private String readNextLine() {
        String nextLine = null;
        do{

            try {
                nextLine = reader.readLine();
            } catch (IOException e) {
                LexemeScannerException.throwIOException(e);
            }
            if (nextLine == null) {//empty
                return "";
            }
            nextLine = nextLine.trim();
        } while (nextLine.isEmpty());
        currentLine = nextLine;
        lineCount++;
        return currentLine;
    }

    /**
     * check if current line is not empty. If empty, run {@link #readNextLine()} to update current line. Trim line
     *each time call this.
     * @return updated line
     */
    private String updateLine()
    {
        currentLine=currentLine.trim();
        if(currentLine.isEmpty())
        {
            return readNextLine();
        }
        return currentLine;
    }

    private ILexeme lastReturned=null;

    @Override
    public ILexeme getNext() {
        ILexeme lexeme;
        while ((lexeme = this.readLexeme()).getLexemeType() == LexemeType.commentBlock || lexeme.getLexemeType() == LexemeType.commentLine) {
            //skip comment
            continue;
        }
        return lexeme;
    }

    @Override
    public ILexeme getNextWithComment() {
        lastReturned=this.readLexeme();
        return lastReturned;
    }

    @Override
    public ILexeme getCurrent() {
        return lastReturned;
    }
}
