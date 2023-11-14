package wang.skycloud.uocsi3120.SyntaxAnalyser;

import wang.skycloud.uocsi3120.Tokenizer.IToken;
import wang.skycloud.uocsi3120.Tokenizer.OperatorToken;
import wang.skycloud.uocsi3120.Tokenizer.OperatorType;
import wang.skycloud.uocsi3120.Tokenizer.SeparatorToken;

public class StatusGenerator {
    public static NodeStatus generate(String code)
    {
        switch (code)
        {
            case "start":
                return new StatusList.start();
            case "ident":
                return new StatusList.ident();
            case "key_for":
                return new StatusList.key_for();
            case "key_if":
                return new StatusList.key_if();
            case "EOF":
                return new StatusList.EOF();
            case "assignment_one_ident":
                return new StatusList.assignment_one_ident();
            case "assignment_wait_right":
                return new StatusList.assignment_wait_right();
            case "bool_wait_operator":
                return new StatusList.bool_wait_operator();
            case "bool_wait_right":
                return new StatusList.bool_wait_right();
            case "for_block_left":
                return new StatusList.for_block_left();
            case "for_wait_block":
                return new StatusList.for_wait_block();
            case "for_wait_first_key":
                return new StatusList.for_wait_first_key();
            case "for_wait_four_ident":
                return new StatusList.for_wait_four_ident();
            case "for_wait_in":
                return new StatusList.for_wait_in();
            case "for_wait_right_parent":
                return new StatusList.for_wait_right_parent();
            case "for_wait_second_ident":
                return new StatusList.for_wait_second_ident();
            case "function_block_left":
                return new StatusList.function_block_left();
            case "function_left_wait_expression":
                return new StatusList.function_left_wait_expression();
            case "function_wait_block":
                return new StatusList.function_wait_block();
            case "function_wait_var_ident":
                return new StatusList.function_wait_var_ident();
            case "if_block_left":
                return new StatusList.if_block_left();
            case "if_wait_block":
                return new StatusList.if_wait_block();
            case "if_wait_expression":
                return new StatusList.if_wait_expression();
            case "semicolon":
                return new StatusList.semicolon();
            case "separator_bracket_left":
                return new StatusList.separator_bracket_left();
            case "var_wait_ident":
                return new StatusList.var_wait_ident();
            case "while_block_left":
                return new StatusList.while_block_left();
            case "while_wait_block":
                return new StatusList.while_wait_block();
            case "while_wait_expression":
                return new StatusList.while_wait_expression();

        }
        throw new RuntimeException("unknown code");
    }
    public static NodeStatus generate(IToken token)
    {
        switch (token.getType())
        {
            case IDENT:
                return new StatusList.ident();
            case KEY:
                switch (token.getContent())
                {
                    case "for":
                        return new StatusList.key_for();
                    case "if":
                        return new StatusList.key_if();
                    case "while":
                        return new StatusList.key_while();
                }
                throw new RuntimeException("unknown key");
            case EOF:
                return new StatusList.EOF();
            case OPERATOR:
                switch(((OperatorToken)token).getOperatorType())
                {
                    case Algorithm:
                        return new StatusList.general("operator_algorithm");
                    case Assign:
                        return new StatusList.general("operator_assign");
                    case Comparator:
                        return new StatusList.general("operator_comparator");
                    default:
                        throw new RuntimeException("unknown operator");
                }
            case SEPARATOR:
                switch (((SeparatorToken)token).getSeparator())
                {
                    case Parentheses:
                        switch (((SeparatorToken)token).getSide())
                        {
                            case Left:
                                return new StatusList.separator_bracket_left();
                            case Right:
                                return new StatusList.general("separator_parent_right");
                        }
                        throw new RuntimeException("unknown parentheses");
                        case Bracket:
                            switch (((SeparatorToken)token).getSide())
                            {
                                case Left:
                                    return new StatusList.separator_bracket_left();
                                case Right:
                                    return new StatusList.general("separator_bracket_right");
                            }
                            throw new RuntimeException("unknown bracket");
                    case Brace:
                        throw new RuntimeException("brace not supported");
                }
            case STRING:
                return new StatusList.general("string");
            case SEMICOLON:
                return new StatusList.semicolon();
            case INT:
                return new StatusList.general("int");
            case FLOAT:
                return new StatusList.general("float");
        }
        throw new RuntimeException("unknown token");
    }
}
