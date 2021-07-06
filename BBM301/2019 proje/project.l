%{
	
#include "y.tab.c"

%}
%option yylineno
alphabetic		[A-Za-z]
digit 			[0-9]
sign			[\-+]
alphanumeric	({alphabetic}|{digit})
%%
int				return(INT);
float			return(FLOAT);
bool			return(BOOL);
void			return(VOID);
char			return(CHAR);
array			return(ARRAY);
file			return(FILETYPE);
dir				return(DIRTYPE);

false			return(BLN_FALSE);
true			return(BLN_TRUE);

"&&"			return(AND_OPT);
"||"			return(OR_OPT);

if				return(IF);
else			return(ELSE);
switch			return(SWITCH);
case			return(CASE);
default			return(DEFAULT);

while			return(WHILE);
do				return(DO);
break			return(BREAK);
continue		return(CONTINUE);
for				return(FOR);

function		return(FUNCTION);
return			return(RETURN);

"print"							return(BLTIN_PRINT);
"showonmap"      				return(SHOW_ON_MAP);
"searchlocation" 				return(SEARCH_LOCATION);
"getroadspeed"   				return(GET_ROAD_SPEED);
"getlocation"    				return(GET_LOCATION);
"showtarget"	 				return(SHOW_TARGET);
"getthetime"     				return(GET_THE_TIME);
"calculatethedistance"			return(CALCULATE_THE_DISTANCE);
"twolocationdistance"   		return(TWO_LOCATION_DISTANCE);
"findthepopularlocation" 		return(FIND_THE_POPULAR_LOCATION);
"commandaboutlocation"   		return(COMMAND_ABOUT_LOCATION);
"collaborationwithotherusers"   return(COLLABORATION_WITH_OTHER_USERS);
"sharescores"					return(SHARE_SCORE);
"setting"						return(SETTING);

"<="			return(LESSEQ_OPT);
">="			return(GREATEREQ_OPT);
"!="			return(NEQ_OPT);
"=="			return(EQ_OPT);
"<"				return(LESS_OPT);
">"				return(GREATER_OPT);

"=*"			return(MULTIPLY_ASSIGNMENT_OPT);
"=/"			return(DIVIDE_ASSIGNMENT_OPT);
"=%"			return(MODE_ASSIGNMENT_OPT);
"=+"			return(ADD_ASSIGNMENT_OPT);
"=-"			return(SUB_ASSIGNMENT_OPT);
"=^"			return(POW_ASSIGNMENT_OPT);
"="				return(ASSIGNMENT_OPT);

"++"			return(INCREMENT_OPT);
"--"			return(DECREMENT_OPT);
"!"				return(NOT_OPT);

"+"				return(ADD_OPT);
"-"				return(SUB_OPT);
"*"				return(MULTIPLY_OPT);
"/"				return(DIVIDE_OPT);
"%"				return(MODE_OPT);
"^"|"**"		return(POW_OPT);

{sign}?{digit}+				return(INT_LTRL);
{sign}?{digit}*"."{digit}+	return(FLT_LTRL);
\"(\\.|[^\\"])*\"			return(STR_LTRL);
\'(\\.|[^\\'])+\'			return(CHR_LTRL);
{alphabetic}({alphanumeric}|_)*	return(IDNTF);
"//"(.*[ \t]*)*[\n]+		;
"/*"(.*[ \t]*[\n]*)*"*/"	;

";"				return(SEMICOLON);
"{"				return(LEFT_BRACKET);
"}"				return(RIGHT_BRACKET);
","				return(COMMA);
":"				return(COLON);
"("				return(LEFT_PARANTHESIS);
")"				return(RIGHT_PARANTHESIS);
"["				return(LEFT_SQ_BRACKET);
"]"				return(RIGHT_SQ_BRACKET);

(\r|\n|\r\n)	;
[ \t\v\f]+		;
.				return(UNKNOWN_CHAR);
%%
