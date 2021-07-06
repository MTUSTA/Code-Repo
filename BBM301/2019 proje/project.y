%token INT FLOAT BOOL VOID CHAR ARRAY FILETYPE DIRTYPE
%token BLN_FALSE BLN_TRUE
%token AND_OPT OR_OPT
%token IF ELSE SWITCH CASE DEFAULT
%token WHILE DO BREAK CONTINUE FOR
%token FUNCTION RETURN
%token BLTIN_PRINT SHOW_ON_MAP SEARCH_LOCATION GET_ROAD_SPEED GET_LOCATION SHOW_TARGET GET_THE_TIME CALCULATE_THE_DISTANCE    
%token TWO_LOCATION_DISTANCE FIND_THE_POPULAR_LOCATION COMMAND_ABOUT_LOCATION COLLABORATION_WITH_OTHER_USERS SHARE_SCORE SETTING
%token LESSEQ_OPT GREATEREQ_OPT NEQ_OPT EQ_OPT LESS_OPT GREATER_OPT 
%token DIVIDE_ASSIGNMENT_OPT ASSIGNMENT_OPT MULTIPLY_ASSIGNMENT_OPT MODE_ASSIGNMENT_OPT ADD_ASSIGNMENT_OPT SUB_ASSIGNMENT_OPT POW_ASSIGNMENT_OPT
%token INCREMENT_OPT DECREMENT_OPT NOT_OPT
%token MULTIPLY_OPT DIVIDE_OPT MODE_OPT ADD_OPT SUB_OPT POW_OPT
%token INT_LTRL FLT_LTRL STR_LTRL CHR_LTRL IDNTF
%token SEMICOLON LEFT_BRACKET RIGHT_BRACKET COMMA COLON LEFT_PARANTHESIS RIGHT_PARANTHESIS LEFT_SQ_BRACKET RIGHT_SQ_BRACKET NEW_LINE WHITE_SPACE UNKNOWN_CHAR
%left ADD_OPT SUB_OPT
%left MULTIPLY_OPT DIVIDE_OPT MODE_OPT
%left POW_OPT
%%
program: function 
		| program function 
		;
function: FUNCTION return_type IDNTF LEFT_PARANTHESIS parameter_list RIGHT_PARANTHESIS block
		;
return_type: data_type		
		;
parameter_list: empty 
		| data_type IDNTF
		| parameter_list COMMA data_type IDNTF
		| VOID
		;
block: LEFT_BRACKET statement_list RIGHT_BRACKET
		| LEFT_BRACKET empty RIGHT_BRACKET
		;
statement_list: statement
		| statement_list statement
		;
statement: assignment SEMICOLON
		| declaration SEMICOLON
		| loop
		| condition
		| function_call SEMICOLON
		| BREAK SEMICOLON
		| CONTINUE SEMICOLON
		| RETURN SEMICOLON
		| RETURN IDNTF SEMICOLON
		| RETURN factor SEMICOLON
		;
declaration: data_type IDNTF 
		| declaration assignment_operator RHS
		| ARRAY data_type IDNTF LEFT_SQ_BRACKET INT_LTRL RIGHT_SQ_BRACKET ASSIGNMENT_OPT LEFT_BRACKET factor_list RIGHT_BRACKET
		;
factor_list: factor
		| factor_list COMMA factor
		;
RHS: arithmetic_expression
		| function_call
		| boolean_expression
		;
function_call: BLTIN_PRINT LEFT_PARANTHESIS identifier_list RIGHT_PARANTHESIS
		| SHOW_ON_MAP LEFT_PARANTHESIS FLT_LTRL COMMA FLT_LTRL RIGHT_PARANTHESIS
		| SHOW_ON_MAP LEFT_PARANTHESIS INT_LTRL COMMA INT_LTRL RIGHT_PARANTHESIS
		| SHOW_ON_MAP LEFT_PARANTHESIS FLT_LTRL COMMA INT_LTRL RIGHT_PARANTHESIS
		| SHOW_ON_MAP LEFT_PARANTHESIS INT_LTRL COMMA FLT_LTRL RIGHT_PARANTHESIS

		| SEARCH_LOCATION LEFT_PARANTHESIS STR_LTRL RIGHT_PARANTHESIS
		| GET_ROAD_SPEED LEFT_PARANTHESIS FLT_LTRL RIGHT_PARANTHESIS
		| GET_ROAD_SPEED LEFT_PARANTHESIS INT_LTRL RIGHT_PARANTHESIS
		| GET_LOCATION LEFT_PARANTHESIS STR_LTRL RIGHT_PARANTHESIS
        | SHOW_TARGET LEFT_PARANTHESIS STR_LTRL RIGHT_PARANTHESIS
		| CALCULATE_THE_DISTANCE LEFT_PARANTHESIS INT_LTRL COMMA INT_LTRL COMMA INT_LTRL COMMA INT_LTRL RIGHT_PARANTHESIS
		| GET_THE_TIME LEFT_PARANTHESIS INT_LTRL COMMA INT_LTRL RIGHT_PARANTHESIS
		| GET_THE_TIME LEFT_PARANTHESIS FLT_LTRL COMMA INT_LTRL RIGHT_PARANTHESIS
		| GET_THE_TIME LEFT_PARANTHESIS INT_LTRL COMMA FLT_LTRL RIGHT_PARANTHESIS
		| GET_THE_TIME LEFT_PARANTHESIS FLT_LTRL COMMA FLT_LTRL RIGHT_PARANTHESIS
		| TWO_LOCATION_DISTANCE LEFT_PARANTHESIS STR_LTRL COMMA STR_LTRL RIGHT_PARANTHESIS
		| FIND_THE_POPULAR_LOCATION LEFT_PARANTHESIS STR_LTRL COMMA INT_LTRL RIGHT_PARANTHESIS
		| COMMAND_ABOUT_LOCATION LEFT_PARANTHESIS STR_LTRL RIGHT_PARANTHESIS
		| COLLABORATION_WITH_OTHER_USERS LEFT_PARANTHESIS STR_LTRL COMMA INT_LTRL RIGHT_PARANTHESIS
		| SHARE_SCORE LEFT_PARANTHESIS str_or_void RIGHT_PARANTHESIS
		| SETTING LEFT_PARANTHESIS str_or_void RIGHT_PARANTHESIS
		;
identifier_list: empty 
		| IDNTF 
		| identifier_list COMMA IDNTF
		| factor
		| identifier_list COMMA factor
		;
str_or_void: COMMA STR_LTRL
		| empty
		;
arithmetic_expression: term
		| arithmetic_expression ADD_OPT term
		| arithmetic_expression SUB_OPT term
		;
term: factor
		| term MULTIPLY_OPT factor
		| term DIVIDE_OPT factor
		| term POW_OPT factor
		| term MODE_OPT factor
		;
factor: INT_LTRL
		| FLT_LTRL 
		| STR_LTRL
		| CHR_LTRL	
		;
assignment_operator: ASSIGNMENT_OPT 
		| MULTIPLY_ASSIGNMENT_OPT 
		| DIVIDE_ASSIGNMENT_OPT 
		| ADD_ASSIGNMENT_OPT 
		| SUB_ASSIGNMENT_OPT 
		| MODE_ASSIGNMENT_OPT
		| POW_ASSIGNMENT_OPT
		;
assignment: LHS assignment_operator RHS
		| LHS INCREMENT_OPT 
		| LHS DECREMENT_OPT
		;
LHS: IDNTF
		| IDNTF LEFT_SQ_BRACKET INT_LTRL RIGHT_SQ_BRACKET
		;	
loop 	
		: while_loop
		| do_while_loop 
		| for_loop
		;
while_loop: WHILE LEFT_PARANTHESIS boolean_expression RIGHT_PARANTHESIS block 
		;
do_while_loop: do_statement WHILE LEFT_PARANTHESIS boolean_expression RIGHT_PARANTHESIS SEMICOLON
		;
do_statement: DO block
		;
for_loop: FOR LEFT_PARANTHESIS for_statement RIGHT_PARANTHESIS block
		;
for_statement: initialize SEMICOLON boolean_expression SEMICOLON assignment
		;
initialize: declaration
		| assignment
		;
condition: if_statement
		| switch_statement
		;
if_statement: IF LEFT_PARANTHESIS boolean_expression RIGHT_PARANTHESIS block
		| IF LEFT_PARANTHESIS function_call RIGHT_PARANTHESIS block
		| if_statement ELSE IF LEFT_PARANTHESIS boolean_expression RIGHT_PARANTHESIS block
		| if_statement ELSE block
		;
boolean_expression: comparison
		| IDNTF
		| BLN_FALSE 
		| BLN_TRUE
		| NOT_OPT IDNTF
		;
comparison: boolean_expression relational_operators compared
		| boolean_expression boolean_operators compared
		| function_call relational_operators compared
		;
compared: IDNTF
		| BLN_FALSE 
		| BLN_TRUE
		| factor
		;
relational_operators: LESSEQ_OPT
		| GREATEREQ_OPT 
		| NEQ_OPT 
		| EQ_OPT 
		| LESS_OPT 
		| GREATER_OPT
		;
boolean_operators: AND_OPT 
		| OR_OPT 
		;
switch_statement: SWITCH LEFT_PARANTHESIS IDNTF RIGHT_PARANTHESIS LEFT_BRACKET case_statement RIGHT_BRACKET 
		| SWITCH LEFT_PARANTHESIS IDNTF LEFT_SQ_BRACKET IDNTF RIGHT_SQ_BRACKET RIGHT_PARANTHESIS LEFT_BRACKET case_statement RIGHT_BRACKET 
		;
case_statement: CASE IDNTF COLON statement_list
		| CASE factor COLON statement_list
		| case_statement CASE IDNTF COLON statement_list
		| case_statement CASE factor COLON statement_list
	    | case_statement DEFAULT COLON statement_list
		;
data_type: CHAR 
		| INT 
		| FLOAT 
		| BOOL
		| FILETYPE
		| DIRTYPE
		;
empty: /* empty */
		;
%%


int main(void) {
    if(yyparse()==0){
    	printf("Can compile successfully\n");
    }
    else {
    	printf("Syntax Error\n");
    }
    return 0;
}
void yyerror(char *s) {
    fprintf(stderr, "line %d: %s\n", yylineno, s);
}