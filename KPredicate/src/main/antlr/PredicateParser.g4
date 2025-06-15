parser grammar PredicateParser;

options { tokenVocab=PredicateLexer; }

// 语法规则
predicate: expr EOF;
expr: comparison (logicOp comparison)*;
logicOp: AND | OR;
comparison: IDENTIFIER op value | LPAREN expr RPAREN;
op: EQUALS | NOT_EQUALS | GREATER | LESS | GREATER_EQUALS | LESS_EQUALS;
value: STRING | NUMBER | BOOLEAN;