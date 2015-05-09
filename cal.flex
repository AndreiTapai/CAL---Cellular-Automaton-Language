%%

%byaccj

%{
	private Cal yyparser;
	public Yylex(java.io.Reader r, Cal yyparser){
		this(r);
		this.yyparser = yyparser;
	}
%}

DIGIT = [0-9]+ ("." [0-9]+)?
ID = [a-zA-Z_][a-zA-Z0-9_]*

%%

/*integers*/						
["+"|"-"]?{DIGIT}+					{ yyparser.yylval = new CalVal(Integer.parseInt(yytext())); return Cal.INTEGERVAL; }
/*floats*/
["+"|"-"]?{DIGIT}+"."{DIGIT}*		{ yyparser.yylval = new CalVal(Double.parseDouble(yytext())); return Cal.FLOATVAL; }
/*gridsize*/
[1-9]+{DIGIT}*x[1-9]{DIGIT}*		{ return Cal.GRIDSIZE; }

/*literals*/
'(\\.|[^\'])'						{ yyparser.yylval = new CalVal(yytext()); return Cal.CHARACTERVAL; }
\"(\\.|[^\"])*\"					{ yyparser.yylval = new CalVal(yytext()); return Cal.STRINGVAL; }

/*comments*/
>[^<]*<								{}
/*keywords*/
grid 								{ yyparser.yylval = new CalVal(yytext()); return Cal.GRID; }
cell								{ yyparser.yylval = new CalVal(yytext()); return Cal.CELL; }
cells  								{ yyparser.yylval = new CalVal(yytext()); return Cal.CELLS; }
neighbor  							{ yyparser.yylval = new CalVal(yytext()); return Cal.NEIGHBOR; }
neighbors  							{ yyparser.yylval = new CalVal(yytext()); return Cal.NEIGHBORS; }
square								{ yyparser.yylval = new CalVal(yytext()); return Cal.SQUARE;}
triangular 							{ yyparser.yylval = new CalVal(yytext()); return Cal.TRIANGULAR;}
hexagonal  							{ yyparser.yylval = new CalVal(yytext()); return Cal.HEXAGONAL; }
is 									{ yyparser.yylval = new CalVal(yytext()); return Cal.IS; }
in 									{ yyparser.yylval = new CalVal(yytext()); return Cal.IN; }
have 								{ yyparser.yylval = new CalVal(yytext()); return Cal.HAVE; }
that-is 							{ yyparser.yylval = new CalVal(yytext()); return Cal.THATIS; }    
color       						{ yyparser.yylval = new CalVal(yytext()); return Cal.COLOR; }
static  							{ yyparser.yylval = new CalVal(yytext()); return Cal.STATIC; }
initialize_cells   					{ yyparser.yylval = new CalVal(yytext()); return Cal.INITIALIZE; }
random								{ yyparser.yylval = new CalVal(yytext()); return Cal.RANDOM; }
life								{ yyparser.yylval = new CalVal(yytext()); return Cal.LIFE; }

/*reserved*/
void 								{ yyparser.yylval = new CalVal(yytext()); return Cal.VOID; }
int 								{ yyparser.yylval = new CalVal(yytext()); return Cal.INTEGER; }
float|double 						{ yyparser.yylval = new CalVal(yytext()); return Cal.FLOAT; }
boolean								{ yyparser.yylval = new CalVal(yytext()); return Cal.BOOLEAN; }
char 								{ yyparser.yylval = new CalVal(yytext()); return Cal.CHARACTER; }
string 								{ yyparser.yylval = new CalVal(yytext()); return Cal.STRING; }
true								{ yyparser.yylval = new CalVal(yytext()); return Cal.TRUE; }
false							    { yyparser.yylval = new CalVal(yytext()); return Cal.FALSE; }

/*operators*/
"-"|"+"|"*"|"/"|"="|"|"|"~"			{ return (int) yycharat(0); }
"("|")"|"["|"]"|","|"."				{ return (int) yycharat(0); }
"//"								{ yyparser.yylval = new CalVal(yytext()); return Cal.FLOORDIVIDE; }
"+="								{ yyparser.yylval = new CalVal(yytext()); return Cal.ADDEQUAL; }
"-="								{ yyparser.yylval = new CalVal(yytext()); return Cal.SUBTRACTEQUAL; }
"*="								{ yyparser.yylval = new CalVal(yytext()); return Cal.MULTIPLYEQUAL; }
"/="								{ yyparser.yylval = new CalVal(yytext()); return Cal.DIVIDEEQUAL; }
"%="								{ yyparser.yylval = new CalVal(yytext()); return Cal.MODULOEQUAL; }
"//="								{ yyparser.yylval = new CalVal(yytext()); return Cal.FLOOREQUAL; }
"--"								{ yyparser.yylval = new CalVal(yytext()); return Cal.DECREMENT; }
"++"								{ yyparser.yylval = new CalVal(yytext()); return Cal.INCREMENT; }

/*compare*/
equals								{ yyparser.yylval = new CalVal(yytext()); return Cal.EQUALS; }
greater-than 						{ yyparser.yylval = new CalVal(yytext()); return Cal.GREATER; }
greater-equals						{ yyparser.yylval = new CalVal(yytext()); return Cal.GREATEREQUALS; }
less-than							{ yyparser.yylval = new CalVal(yytext()); return Cal.LESS; }
less-equals							{ yyparser.yylval = new CalVal(yytext()); return Cal.LESSEQUALS; }
not-equals							{ yyparser.yylval = new CalVal(yytext()); return Cal.NOTEQUALS; }

/*logic*/
and 								{ yyparser.yylval = new CalVal(yytext()); return Cal.AND; }
or 									{ yyparser.yylval = new CalVal(yytext()); return Cal.OR; }
xor   								{ yyparser.yylval = new CalVal(yytext()); return Cal.XOR; }
nand 								{ yyparser.yylval = new CalVal(yytext()); return Cal.NAND; }
nor 								{ yyparser.yylval = new CalVal(yytext()); return Cal.NOR; }
not									{ yyparser.yylval = new CalVal(yytext()); return Cal.NOT; }

/*control*/
if 									{ yyparser.yylval = new CalVal(yytext()); return Cal.IF; }
else-if 							{ yyparser.yylval = new CalVal(yytext()); return Cal.ELSEIF; }
else    							{ yyparser.yylval = new CalVal(yytext()); return Cal.ELSE; }
while 								{ yyparser.yylval = new CalVal(yytext()); return Cal.WHILE; }
for 								{ yyparser.yylval = new CalVal(yytext()); return Cal.FOR; }
for-each							{ yyparser.yylval = new CalVal(yytext()); return Cal.FOREACH; }
break								{ yyparser.yylval = new CalVal(yytext()); return Cal.BREAK; }
continue							{ yyparser.yylval = new CalVal(yytext()); return Cal.CONTINUE; }
return								{ yyparser.yylval = new CalVal(yytext()); return Cal.RETURN; }

/*variables*/
{ID}								{ yyparser.yylval = new CalVal(yytext()); return Cal.VARIABLE; }

/*skip tabs and spaces */
[\t\n\r ]| \r\n						{}
\b									{ System.err.println("error: backspace ignored."); }
[^]    								{ System.err.println("error: unexpected character '"+yytext()+"'"); return -1; }