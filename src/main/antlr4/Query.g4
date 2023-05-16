grammar Query;

@parser::header {
package parser;
}

@lexer::header {
package parser;
}
/* Parser Rules */
sql_query :  update_table | insert_into | delete_from | select_from | create_index | create_table;

create_table : CREATE TABLE tableName '(' column_def (',' column_def)* ')' (SEMICOLON)? EOF;

column_def : createColumnName datatype (primaryKey)?;

createColumnName: IDENTIFIER;

primaryKey: PRIMARY KEY;

datatype : INT | DECIMALSTRING | VARCHAR | DATESTRING ;

update_table : UPDATE tableName SET updateColumnToSet (otherUpdateColumnToSet)* WHERE updateDeleteCondition (SEMICOLON)? EOF;



insert_into : INSERT INTO tableName '(' insertColumnName (additionalColumnInsert)* ')' VALUES '(' insertValue (',' insertValue)* ')' (SEMICOLON)? EOF;

value : INTEGER | DECIMAL | STRING | DATE;

insertValue: INTEGER | DECIMAL | STRING | DATE;

delete_from : DELETE FROM tableName (WHERE updateDeleteCondition (otherDeleteCondition)*)? (SEMICOLON)? EOF;

select_from : SELECT '*' FROM tableName (WHERE condition (otherSelectCondition)*)? (SEMICOLON)? EOF;

condition : columnName operator value;

create_index : CREATE INDEX ON tableName '('indexColumnName COMMA indexColumnName COMMA indexColumnName')' (SEMICOLON)? EOF;

tableName : IDENTIFIER;

columnName: IDENTIFIER;

indexColumnName: IDENTIFIER;

operator: GREATERTHAN | GREATERTHANOREQUAL | LESSTHAN | LESSTHANOREQUAL | NOTEQUAL | EQUAL;

otherSelectCondition: columnOperators columnName operator value;

columnOperators: AND | XOR | OR ;

updateDeleteColumnName: IDENTIFIER;

updateDeleteValue: INTEGER | DECIMAL | STRING | DATE;

updateDeleteCondition : updateDeleteColumnName '=' updateDeleteValue;

otherDeleteCondition: AND updateDeleteColumnName '=' updateDeleteValue;

updateColumnName: IDENTIFIER;

updateValue: INTEGER | DECIMAL | STRING | DATE;

updateColumnToSet : updateColumnName '=' updateValue;

otherUpdateColumnToSet : AND updateColumnName '=' updateValue;

insertColumnName: IDENTIFIER;

additionalColumnInsert: ',' insertColumnName ;




/* Lexer Rules */
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];
fragment DAY: [0-9][0-9];
fragment MONTH: [0-9][0-9];
fragment YEAR: [0-9][0-9][0-9][0-9];
fragment A:[aA];
fragment B:[bB];
fragment C:[cC];
fragment D:[dD];
fragment E:[eE];
fragment F:[fF];
fragment G:[gG];
fragment H:[hH];
fragment I:[iI];
fragment J:[jJ];
fragment K:[kK];
fragment L:[lL];
fragment M:[mM];
fragment N:[nN];
fragment O:[oO];
fragment P:[pP];
fragment Q:[qQ];
fragment R:[rR];
fragment S:[sS];
fragment T:[tT];
fragment U:[uU];
fragment V:[vV];
fragment W:[wW];
fragment X:[xX];
fragment Y:[yY];
fragment Z:[zZ];

TABLE : T A B L E;
CREATE : C R E A T E;
UPDATE : U P D A T E;
INSERT : I N S E R T;
DELETE : D E L E T E;
SET : S E T;
SELECT : S E L E C T;
FROM : F R O M;
WHERE : W H E R E;
AND : A N D ;
OR : O R;
XOR: X O R;
INDEX : I N D E X;
ON : O N;
INTO : I N T O;
VALUES : V A L U E S;
PRIMARY: P R I M A R Y;
INT: I N T;
DECIMALSTRING: D E C I M A L;
VARCHAR: V A R C H A R;
DATESTRING : D A T E;
KEY: K E Y;
IDENTIFIER : LETTER (LETTER | DIGIT | '_')*;
INTEGER: DIGIT+;
DECIMAL : DIGIT+ '.' DIGIT*;
STRING : '\'' ~('\''|'\r'|'\n')* '\'';
DATE: YEAR '-' MONTH '-' DAY ;
GREATERTHAN: '>';
GREATERTHANOREQUAL: '>=';
LESSTHAN: '<';
LESSTHANOREQUAL: '<=';
NOTEQUAL: '!=';
EQUAL: '=';
LPAREN : '(';
RPAREN : ')';
COMMA : ',';
SEMICOLON : ';';
WS : [ \t\n\r] + -> skip;
/* End Of Lexer Rules */
