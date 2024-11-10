parser grammar MapleParser;

@header {
package net.rotgruengelb.maple.internal.generated;
}

options {
tokenVocab = MapleLexer;
}

maple
    : pair* EOF
    ;

// Map structure (only for nested maps)
map
    : BRACE_OPEN pair* BRACE_CLOSE
    ;

// Key-value pair
pair
    : KEY EQUALS value
    ;

// Value: string || number || map || list
value
    : STRING
    | NUMBER
    | TRUE
    | FALSE
    | map
    | list
    ;

// List structure
list
    : BRACKET_OPEN value (COMMA? value)* BRACKET_CLOSE
    ;
