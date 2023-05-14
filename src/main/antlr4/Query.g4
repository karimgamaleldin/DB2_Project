grammar Query;

@parser::header {
package sqlAntlrParser;
}

@lexer::header {
package sqlAntlrParser;
}
/* Parser Rules */
sql_query :  update_table | insert_into | delete_from | select_from | create_index | create_table;

create_table : CREATE TABLE tableName '(' column_def (',' column_def)* ')';

column_def : createColumnName datatype (primaryKey)?;

createColumnName: IDENTIFIER;

primaryKey: PRIMARY KEY;

datatype : 'INT' | 'DECIMAL' | 'VARCHAR' | 'DATE' ;

update_table : UPDATE tableName SET updateColumnToSet (otherUpdateColumnToSet)* WHERE updateDeleteCondition ;

insert_into : INSERT INTO tableName '(' insertColumnName (additionalColumnInsert)* ')' VALUES '(' insertValue (',' insertValue)* ')';

value : INTEGER | DECIMAL | STRING;

insertValue: value;

delete_from : DELETE FROM tableName (WHERE updateDeleteCondition (otherDeleteCondition)*)?;

select_from : SELECT '*' FROM tableName (WHERE condition (otherSelectCondition)*)?;

condition : columnName operator value;

create_index : CREATE INDEX ON tableName '('indexColumnName COMMA indexColumnName COMMA indexColumnName')';

tableName : IDENTIFIER;

columnName: IDENTIFIER;

indexColumnName: IDENTIFIER;

operator: GREATERTHAN | GREATERTHANOREQUAL | LESSTHAN | LESSTHANOREQUAL | NOTEQUAL | EQUAL;

otherSelectCondition: columnOperators columnName operator value;

columnOperators: AND | XOR | OR ;

updateDeleteColumnName: IDENTIFIER;

updateDeleteValue: INTEGER | DECIMAL | STRING;

updateDeleteCondition : updateDeleteColumnName '=' updateDeleteValue;

otherDeleteCondition: ',' updateDeleteColumnName '=' updateDeleteValue;

updateColumnName: IDENTIFIER;

updateValue: INTEGER | DECIMAL | STRING;

updateColumnToSet : updateColumnName '=' updateValue;

otherUpdateColumnToSet : ',' updateColumnName '=' updateValue;

insertColumnName: IDENTIFIER;

additionalColumnInsert: ',' insertColumnName ;




/* Lexer Rules */
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];
TABLE : 'TABLE';
CREATE : 'CREATE';
UPDATE : 'UPDATE';
INSERT : 'INSERT';
DELETE : 'DELETE';
SET : 'SET';
SELECT : 'SELECT';
FROM : 'FROM';
WHERE : 'WHERE';
AND : 'AND';
OR : 'OR';
XOR: 'XOR';
INDEX : 'INDEX';
ON : 'ON';
INTO : 'INTO';
VALUES : 'VALUES';
PRIMARY: 'PRIMARY';
KEY: 'KEY';
IDENTIFIER : LETTER (LETTER | DIGIT | '_')*;
INTEGER: DIGIT+;
DECIMAL : DIGIT+ '.' DIGIT*;
STRING : '\'' ~('\''|'\r'|'\n')* '\'';
GREATERTHAN: '>';
GREATERTHANOREQUAL: '>=';
LESSTHAN: '<';
LESSTHANOREQUAL: '<=';
NOTEQUAL: '!=';
EQUAL: '=';
LPAREN : '(';
RPAREN : ')';
COMMA : ',';
WS : [ \t\n\r] + -> skip;
/* End Of Lexer Rules */
