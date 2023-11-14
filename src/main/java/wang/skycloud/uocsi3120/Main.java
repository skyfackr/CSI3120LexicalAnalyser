package wang.skycloud.uocsi3120;

import wang.skycloud.uocsi3120.LexemeScanner.ILexeme;
import wang.skycloud.uocsi3120.LexemeScanner.LScanner;
import wang.skycloud.uocsi3120.LexemeScanner.LScannerFactory;
import wang.skycloud.uocsi3120.LexemeScanner.LexemeType;
import wang.skycloud.uocsi3120.SyntaxAnalyser.SAFactory;
import wang.skycloud.uocsi3120.Tokenizer.IToken;
import wang.skycloud.uocsi3120.Tokenizer.TKFactory;

import java.util.ArrayList;
import java.util.List;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) {
        //arg1: input file
        if (args.length != 1) {
            System.out.println("Usage: java -jar Analyser.jar <input file> ");
            return;
        }
        String input=args[0];

        LScanner scanner = LScannerFactory.createScanner(input);
        List<ILexeme> lexemes=new ArrayList<>();
        do {
            lexemes.add(scanner.getNext());
        }while (scanner.getCurrent().getLexemeType()!= LexemeType.EOF);
        Iterable<IToken> tokens= TKFactory.tokenize(lexemes);
        try {
            SAFactory.analyze(tokens);
        }catch (RuntimeException e)
        {
            System.out.println("Syntax analysis failed.");
            return;
        }
        System.out.println("Syntax analysis succeed.");

    }
}