lexer grammar PredicateLexer;

// 1. 关键字和操作符
BOOLEAN: 'true' | 'false' | 'TRUE' | 'FALSE';
AND: 'AND' | 'and' | '&&';
OR: 'OR' | 'or' | '||';
EQUALS: '==';
NOT_EQUALS: '!=';
GREATER: '>';
LESS: '<';
GREATER_EQUALS: '>=';
LESS_EQUALS: '<=';
LPAREN: '(';
RPAREN: ')';

// 2. 标识符和字面量
IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;
STRING: '"' (~["\\\r\n] | EscapeSequence)* '"';
NUMBER: [0-9]+ ('.' [0-9]+)?;

// 3. 辅助规则
fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;
fragment HexDigit: [0-9a-fA-F];

// 4. 空白字符
WS: [ \t\r\n]+ -> skip;