package wang.skycloud.uocsi3120.SyntaxAnalyser;

import java.util.Map;
import java.util.Set;

public class StatusList {
    /* condition is token type
    [status] -> [next allowed(condition, status),...],[change (condition, result),...],[Elimination]
    start -> [ident(ident),key([specific key status]),separator_bracket_left(separator_bracket_left)],[],[EOF]
    ident -> [],[(operator_assign,assignment_wait_right),(separator_parent_left,function_left_wait_expression)],[semicolon]
    key_for -> [],[(separator_parent_left,for_wait_first_key)],[]
    key_if -> [],[(separator_parent_left,if_wait_expression)],[]
    key_while -> [],[(separator_parent_left,while_wait_expression)],[]
    separator_bracket_left -> [],[],[separator_bracket_right]
    assignment_wait_right -> [],[(ident, assignment_one_ident)],[]
    function_left_wait_expression -> [int/string/float(function_wait_var_ident)],[],[separator_parent_right]
    for_wait_first_key -> [],[int/float/string(for_wait_second_ident)],[]
    if_wait_expression -> [ident(bool_wait_operator)],[(separator_parent_right,if_wait_block)],[]
    while_wait_expression -> [ident(bool_wait_operator)],[(separator_parent_right,while_wait_block)],[]
    assignment_one_ident -> [],[(operator_algorithm,assignment_wait_right)],[semicolon]
    function_wait_var_ident -> [key(var_wait_ident)],[(separator_parent_right,function_wait_block)],[]
    for_wait_second_ident -> [],[(ident,for_wait_in)],[]
    bool_wait_operator -> [],[(operator_comparator,bool_wait_right)],[]
    if_wait_block -> [separator_bracket_left(if_block_left)],[],[if_block_complete]
    while_wait_block -> [separator_bracket_left(while_block_left)],[],[while_block_complete]
    var_wait_ident -> [],[],[ident]
    function_wait_block -> [separator_bracket_left(function_block_left)],[],[function_block_complete]
    for_wait_in -> [],[("in"(for_wait_four_ident))],[]
    bool_wait_right -> [],[],[string/int/float]
    if_block_left -> [ident(ident),key([specific key status]),separator_bracket_left(separator_bracket_left)],[separator_bracket_right(if_block_complete)],[]
    while_block_left -> [ident(ident),key([specific key status]),separator_bracket_left(separator_bracket_left)],[separator_bracket_right(while_block_complete)],[]
    function_block_left -> [ident(ident),key([specific key status]),separator_bracket_left(separator_bracket_left)],[separator_bracket_right(function_block_complete)],[]
    for_wait_four_ident -> [],[(ident,for_wait_right_parent)],[]
    for_wait_right_parent -> [],[(separator_parent_right,for_wait_block)],[]
    for_wait_block -> [separator_bracket_left(for_block_left)],[],[for_block_complete]
    for_block_left -> [ident(ident),key([specific key status]),separator_bracket_left(separator_bracket_left)],[separator_bracket_right(for_block_complete)],[]
    * */
    static class start extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("why?");
        }

        start() {
            updateNextAllowed(Map.of(
                    "ident", "ident",
                    "key_for", "key_for",
                    "key_if", "key_if",
                    "key_while", "key_while",
                    "separator_bracket_left", "separator_bracket_left"
            ));
            updateChangedWhen(Map.of(
                    "separator_bracket_left", "separator_bracket_left"
            ));
            updateDeleteWhen(Set.of(
                    "EOF"
            ));
            code = "start";
        }
    }

    static class ident extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing operator or \"(\"");
        }

        ident() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "operator_assign", "assignment_wait_right",
                    "separator_parent_left", "function_left_wait_expression"
            ));
            updateDeleteWhen(Set.of(
                    "semicolon"
            ));
            code = "ident";
        }
    }

    static class key_for extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"(\"");
        }

        key_for() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "separator_parent_left", "for_wait_first_key"
            ));
            updateDeleteWhen(Set.of());
            code = "key_for";
        }
    }

    static class key_if extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"(\"");
        }

        key_if() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "separator_parent_left", "if_wait_expression"
            ));
            updateDeleteWhen(Set.of());
            code = "key_if";
        }
    }

    static class key_while extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"(\"");
        }

        key_while() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "separator_parent_left", "while_wait_expression"
            ));
            updateDeleteWhen(Set.of());
            code = "key_while";
        }
    }

    static class separator_bracket_left extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"}\"");
        }

        separator_bracket_left() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of());
            updateDeleteWhen(Set.of(
                    "separator_bracket_right"
            ));
            code = "separator_bracket_left";
        }
    }

    static class assignment_wait_right extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("assignment error");
        }

        assignment_wait_right() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "ident", "assignment_one_ident"
            ));
            updateDeleteWhen(Set.of());
            code = "assignment_wait_right";
        }
    }

    static class function_left_wait_expression extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing var type");
        }

        function_left_wait_expression() {
            updateNextAllowed(Map.of(
                    "int", "function_wait_var_ident",
                    "string", "function_wait_var_ident",
                    "float", "function_wait_var_ident"
            ));
            updateChangedWhen(Map.of());
            updateDeleteWhen(Set.of(
                    "separator_parent_right"
            ));
            code = "function_left_wait_expression";
        }
    }

    static class for_wait_first_key extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing for element");
        }

        for_wait_first_key() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "int", "for_wait_second_ident",
                    "float", "for_wait_second_ident",
                    "string", "for_wait_second_ident"
            ));
            updateDeleteWhen(Set.of());
            code = "for_wait_first_key";
        }
    }
    static class if_wait_expression extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("illegal if expression");
        }

        if_wait_expression() {
            updateNextAllowed(Map.of(
                    "ident", "bool_wait_operator"
            ));
            updateChangedWhen(Map.of(
                    "separator_parent_right", "if_wait_block"
            ));
            updateDeleteWhen(Set.of());
            code = "if_wait_expression";
        }
    }
    static class while_wait_expression extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("illegal while expression");
        }

        while_wait_expression() {
            updateNextAllowed(Map.of(
                    "ident", "bool_wait_operator"
            ));
            updateChangedWhen(Map.of(
                    "separator_parent_right", "while_wait_block"
            ));
            updateDeleteWhen(Set.of());
            code = "while_wait_expression";
        }
    }
    static class assignment_one_ident extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing operator");
        }

        assignment_one_ident() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "operator_algorithm", "assignment_wait_right"
            ));
            updateDeleteWhen(Set.of(
                    "semicolon"
            ));
            code = "assignment_one_ident";
        }
    }
    static class function_wait_var_ident extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing var ident");
        }

        function_wait_var_ident() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of());
            updateDeleteWhen(Set.of(
                    "ident"
            ));
            code = "function_wait_var_ident";
        }
    }
    static class for_wait_second_ident extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing for ident name");
        }

        for_wait_second_ident() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "ident", "for_wait_in"
            ));
            updateDeleteWhen(Set.of());
            code = "for_wait_second_ident";
        }
    }
    static class bool_wait_operator extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing boolean operator");
        }

        bool_wait_operator() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "operator_comparator", "bool_wait_right"
            ));
            updateDeleteWhen(Set.of());
            code = "bool_wait_operator";
        }
    }
    static class if_wait_block extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"{\"");
        }

        if_wait_block() {
            updateNextAllowed(Map.of(
                    "separator_bracket_left", "if_block_left"
            ));
            updateChangedWhen(Map.of());
            updateDeleteWhen(Set.of(
                    "if_block_complete"
            ));
            code = "if_wait_block";
        }
    }
    static class while_wait_block extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"{\"");
        }

        while_wait_block() {
            updateNextAllowed(Map.of(
                    "separator_bracket_left", "while_block_left"
            ));
            updateChangedWhen(Map.of());
            updateDeleteWhen(Set.of(
                    "while_block_complete"
            ));
            code = "while_wait_block";
        }
    }
    static class var_wait_ident extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing ident");
        }

        var_wait_ident() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of());
            updateDeleteWhen(Set.of(
                    "ident"
            ));
            code = "var_wait_ident";
        }
    }
    static class function_wait_block extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"{\"");
        }

        function_wait_block() {
            updateNextAllowed(Map.of(
                    "separator_bracket_left", "function_block_left"
            ));
            updateChangedWhen(Map.of());
            updateDeleteWhen(Set.of(
                    "function_block_complete"
            ));
            code = "function_wait_block";
        }
    }
    static class for_wait_in extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing keyword \"in\"");
        }

        for_wait_in() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "in", "for_wait_four_ident"
            ));
            updateDeleteWhen(Set.of());
            code = "for_wait_in";
        }
    }
    static class bool_wait_right extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing operand at right of operator");
        }

        bool_wait_right() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of());
            updateDeleteWhen(Set.of(
                    "string",
                    "int",
                    "float"
            ));
            code = "bool_wait_right";
        }
    }
    static class if_block_left extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"}\"");
        }

        if_block_left() {
            updateNextAllowed(Map.of(
                    "ident", "ident",
                    "key_for", "key_for",
                    "key_if", "key_if",
                    "key_while", "key_while",
                    "separator_bracket_left", "separator_bracket_left"
            ));
            updateChangedWhen(Map.of("separator_bracket_right", "if_block_complete"));
            updateDeleteWhen(Set.of(

            ));
            code = "if_block_left";
        }
    }
    static class while_block_left extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"}\"");
        }

        while_block_left() {
            updateNextAllowed(Map.of(
                    "ident", "ident",
                    "key_for", "key_for",
                    "key_if", "key_if",
                    "key_while", "key_while",
                    "separator_bracket_left", "separator_bracket_left"
            ));
            updateChangedWhen(Map.of("separator_bracket_right","while_block_complete"));
            updateDeleteWhen(Set.of(

            ));
            code = "while_block_left";
        }
    }
    static class function_block_left extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"}\"");
        }

        function_block_left() {
            updateNextAllowed(Map.of(
                    "ident", "ident",
                    "key_for", "key_for",
                    "key_if", "key_if",
                    "key_while", "key_while",
                    "separator_bracket_left", "separator_bracket_left"
            ));
            updateChangedWhen(Map.of("separator_bracket_right","function_block_complete"));
            updateDeleteWhen(Set.of(

            ));
            code = "function_block_left";
        }
    }
    static class for_wait_four_ident extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("for missing ident");
        }

        for_wait_four_ident() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "ident", "for_wait_right_parent"
            ));
            updateDeleteWhen(Set.of());
            code = "for_wait_four_ident";
        }
    }
    static class for_wait_right_parent extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \")\"");
        }

        for_wait_right_parent() {
            updateNextAllowed(Map.of());
            updateChangedWhen(Map.of(
                    "separator_parent_right", "for_wait_block"
            ));
            updateDeleteWhen(Set.of());
            code = "for_wait_right_parent";
        }
    }
    static class for_wait_block extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"{\"");
        }

        for_wait_block() {
            updateNextAllowed(Map.of(
                    "separator_bracket_left", "for_block_left"
            ));
            updateChangedWhen(Map.of());
            updateDeleteWhen(Set.of(
                    "for_block_complete"
            ));
            code = "for_wait_block";
        }
    }
    static class for_block_left extends NodeStatus {
        @Override
        protected void customError() {
            throw new RuntimeException("missing \"}\"");
        }
            for_block_left() {
                updateNextAllowed(Map.of(
                        "ident", "ident",
                        "key_for", "key_for",
                        "key_if", "key_if",
                        "key_while", "key_while",
                        "separator_bracket_left", "separator_bracket_left"
                ));
                updateChangedWhen(Map.of("separator_bracket_right","for_block_complete"));
                updateDeleteWhen(Set.of(

                ));
                code = "for_block_left";
            }
    }
    static class semicolon extends NodeStatus
    {
        @Override
        protected void customError() {
            throw new RuntimeException("Unexcepted semicolon");
        }
        semicolon()
        {
            code="semicolon";
        }
    }
    static class EOF extends NodeStatus
    {
        @Override
        protected void customError() {
            throw new RuntimeException("Something missing");
        }
        EOF()
        {
            code="EOF";
        }
    }
}

