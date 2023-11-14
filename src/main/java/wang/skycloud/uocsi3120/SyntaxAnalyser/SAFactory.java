package wang.skycloud.uocsi3120.SyntaxAnalyser;

import wang.skycloud.uocsi3120.Tokenizer.IToken;

import java.util.Iterator;

public class SAFactory {
    public static void analyze(Iterable<IToken> tokens)
    {
        IToken now = null;
        try {
            StatusStack stack = new StatusStack();
            for (IToken token : tokens) {
                if (stack.isEmpty())
                    throw new RuntimeException("unexcepted EOF");
                now = token;
                stack.doOperation(stack.peek().nextOperation(StatusGenerator.generate(now)));
            }
            if (!stack.isEmpty())
                throw new RuntimeException("except EOF");
        }catch (RuntimeException e)
        {
            System.out.println("syntax_analyzer_error - "+e.getMessage()+" at line "+now.getLine());
            throw e;
        }
    }
}
