lexer grammar MapleLexer;

@header {
package net.rotgruengelb.maple.internal.generated;
}

// Lexer rules
BRACE_OPEN    : '{';
BRACE_CLOSE   : '}';
BRACKET_OPEN  : '[';
BRACKET_CLOSE : ']';
COMMA         : ',';
EQUALS        : '=';
STRING        : '\'' (~['\\] | '\\\'')* '\'';
NUMBER        : '-'? [0-9]+ ('.' [0-9]+)?;
TRUE          : 'true';  // Boolean true
FALSE         : 'false'; // Boolean false
KEY           : [a-zA-Z_][a-zA-Z_0-9]*; // Alphanumeric Keys

// Send whitespace and semicolons to the hidden channel
WS            : [ \t\r\n]+ -> channel(HIDDEN);
SEMICOLON     : ';' -> channel(HIDDEN);

// Comments
COMMENT       : '//' ~[\r\n]* -> channel(HIDDEN);
ML_COMMENT    : '/*' .*? '*/' -> channel(HIDDEN);
