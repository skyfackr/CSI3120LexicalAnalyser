package wang.skycloud.uocsi3120.SyntaxAnalyser;

import java.util.Map;

public class StatusList {
    /* condition is token type
    [status] -> [next allowed(condition, status),...],[change (condition, result),...],[Elimination]
    start -> [ident(ident),key([specific key status]),separator_bracket_left(separator_bracket_left)],[],[EOF]
    ident -> [],[(operator_assign,assignment_wait_right),(separator_parent_left,function_left_wait_expression)],[semicolon]
    key_for -> [],[(separator_parent_left,for_wait_first_key)],[]
    key_if -> [],[(separator_parent_left,if_wait_expression)],[]
    key_while -> [],[(separator_parent_left,while_wait_expression)],[]
    separator_bracket_left -> [],[],[separator_bracket_right]
    assignment_wait_right -> [],[(string/int/float, assignment_one_ident)],[]
    function_left_wait_expression -> [key(function_wait_var_ident)],[],[separator_parent_right]
    for_wait_first_key -> [],[key(for_wait_second_ident)],[]
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
    class start extends NodeStatus
    {
        @Override
        protected void customError() {
            //TODO: customError not implemented
            throw new RuntimeException("customError not implemented");
        }
        Map<String,String> nextAllowed=Map.of("1","2");
    }
}
