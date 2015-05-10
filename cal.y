%{
  import java.io.*;
  import cal.essentials.AbstractNode;
  import cal.essentials.FunctionDeclarationNode;
    import cal.essentials.FunctionBlockNode;
  import cal.essentials.ParametersNode;
  import cal.essentials.ReturnsNode;
  import cal.essentials.StatementsNode;
%}

%token INTEGERVAL FLOATVAL CHARACTERVAL STRINGVAL TRUE FALSE GRIDSIZE     
%token GRID CELL CELLS NEIGHBOR NEIGHBORS RANDOM LIFE TRIANGULAR SQUARE HEXAGONAL
%token THATIS COLOR STATIC INITIALIZE IS HAVE
%token VOID INTEGER FLOAT BOOLEAN CHARACTER STRING
%token AND OR NOR XOR NAND NOT
%token IF ELSEIF ELSE FOR WHILE FOREACH IN
%token CONTROL RETURN CONTINUE BREAK NEWLINE
%token ADDEQUAL SUBTRACTEQUAL MULTIPLYEQUAL DIVIDEEQUAL MODULOEQUAL FLOOREQUAL
%token DECREMENT INCREMENT EQUALS NOTEQUALS GREATER GREATEREQUALS LESS LESSEQUALS FLOORDIVIDE
%token VARIABLE

%right IF ELSEIF ELSE 
%left AND OR NOR XOR NAND
%left '-' '+' '.'
%left '*' '/' FLOORDIVIDE
%left DECREMENT INCREMENT
%right '=' '^' IS NOT        /* exponentiation        */
%right ADDEQUAL SUBTRACTEQUAL MULTIPLYEQUAL DIVIDEEQUAL MODULOEQUAL FLOOREQUAL
      
%%

program             : statements                                            { $$ = $1; }
                    ;
statements          : statement                                             { $$ = $1; } 
                    | statement statements                                  { $$ = $1; }
                    ;
statement           : headerStatement                                       { $$ = $1; }
                    | variableStatement                                     { $$ = $1; }
                    | functionStatement                                     { $$ = $1; }
                    | continuation                                          { $$ = $1; }
                    | expressionStatement                                   { $$ = $1; }
                    | iteration                                             { $$ = $1; }
                    ;
headerStatement     : gridDefinition                                        { $$ = $1; }
                    | cellDefinition                                        { $$ = $1; }
                    ;
variableStatement   : variableDeclaration                                   { $$ = $1; }
                    | variableDefinition                                    { $$ = $1; }
                    ;
functionStatement   : functionDeclaration                                   { $$ = $1; }
                    | functionCall                                          { $$ = $1; }
                    ;
continuation        : CONTINUE
                    | BREAK
                    ;
expressionStatement : variable                                              { $$ = $1; }
                    | variable INCREMENT
                    | variable DECREMENT
                    | value                                                 { $$ = $1; }
                    ;
expression          : expression '+' expression
                    | expression '-' expression
                    | expression '*' expression
                    | expression '/' expression
                    | expression '^' expression
                    | expression FLOORDIVIDE expression
                    | variable                                              { $$ = $1; }
                    | variable INCREMENT
                    | variable DECREMENT
                    | VARIABLE '[' arrayIndex ']'
                    | value                                                 { $$ = $1; }
                    ;
conditional         : expression condition expression
                    | expression condition expression logic conditional
                    | NOT conditional
                    ;
iteration           : IF '(' conditional ')' block
                    | IF '(' conditional ')' block elseif
                    | IF '(' conditional ')' block elseif ELSE block
                    | IF '(' conditional ')' block ELSE block
                    | FOR '(' forStatement ',' conditional ',' forStatement ')' block
                    | FOREACH '(' iterable IN iterables ')' block
                    | WHILE '(' conditional ')' block
                    ;
gridDefinition      : GRID VARIABLE IS GRIDSIZE 
                    | VARIABLE IS gridtype
                    ;
cellDefinition      : CELLS HAVE VARIABLE
                    | CELLS HAVE LIFE
                    ;
variableDeclaration : type VARIABLE 
                    | type '[' INTEGERVAL ']' VARIABLE
                    ;                   
variableDefinition  : type VARIABLE '=' expression
                    | VARIABLE '[' arrayIndex ']' '=' expression 
                    | variable '=' expression
                    | variable '=' functionCall
                    | variable assign expression
                    ;
functionDeclaration : type VARIABLE '(' parameters ')' functionBlock {
                        $$ = new FunctionDeclarationNode($1.sval, $2.sval, (ParametersNode)$4, (FunctionBlockNode)$5);
                    }
                    ;
functionCall        : VARIABLE '(' actuals ')'
                    | RANDOM '(' randomActuals')'
                    ;
forStatement        : variableStatement                                     { $$ = $1; }
                    | variable INCREMENT
                    | variable DECREMENT
                    ;
elseif              : ELSEIF '(' conditional ')' block %prec IF
                    | ELSEIF '(' conditional ')' block elseif
                    ;
variable            : VARIABLE
                    | CELL
                    | CELL '.' LIFE
                    | CELL'.' VARIABLE
                    | CELLS '.' LIFE
                    | CELLS '.' VARIABLE
                    | NEIGHBOR '.' LIFE
                    | NEIGHBOR '.' VARIABLE
                    | NEIGHBORS '.' LIFE
                    | NEIGHBORS '.' VARIABLE
                    | CELL '.' VARIABLE '[' arrayIndex ']'
                    | NEIGHBOR '.' VARIABLE '[' arrayIndex ']'
                    | CELLS '.' VARIABLE '[' arrayIndex ']'
                    | NEIGHBORS '.' VARIABLE '[' arrayIndex ']'
                    ;
iterable            : CELL
                    | NEIGHBOR
                    ;
iterables           : CELLS
                    | NEIGHBORS
                    ;
type                : INTEGER
                    | FLOAT
                    | BOOLEAN
                    | CHARACTER
                    | STRING
                    | VOID
                    | NEIGHBOR
                    ;
value               : INTEGERVAL
                    | FLOATVAL
                    | TRUE
                    | FALSE
                    | CHARACTERVAL
                    | STRINGVAL
                    ;
assign              : ADDEQUAL
                    | SUBTRACTEQUAL
                    | MULTIPLYEQUAL
                    | DIVIDEEQUAL
                    | MODULOEQUAL
                    | FLOOREQUAL
                    ;
condition           : EQUALS
                    | GREATER
                    | GREATEREQUALS
                    | LESS
                    | LESSEQUALS
                    | NOTEQUALS
                    ;
logic               : AND
                    | OR
                    | NOR
                    | NAND
                    | XOR
                    ;
block               : '|' statements '|'
                    | statement
                    | '|' '|'
                    ;
functionBlock       : '|' statements return '|'     { $$ = new FunctionBlockNode($2, $3); }
                    | '|' return '|'                { $$ = new FunctionBlockNode($2); }
                    | '|' statements'|'             { $$ = new FunctionBlockNode($2); }
                    | '|' '|'                       { $$ = new FunctionBlockNode(); }
                    ;
return              : RETURN value                  { $$ = new ReturnsNode($2.dval); } 
                    | RETURN variable               { $$ = new ReturnsNode($2.sval); }
                    ;
parameters          :                               { $$ = new ParametersNode(); }
                    | type VARIABLE                 { $$ = new ParametersNode($1.sval, $2.sval); }
                    | type VARIABLE ',' parameters  { $$ = new ParametersNode($1, $2, $3); }
                    | CELL VARIABLE                 { $$ = new ParametersNode($1, $2); }
                    ;
actuals             : variable
                    | variable ',' actuals
                    ;
randomActuals       : 
                    | value
                    | value ',' randomActuals
                    | value '~' value
                    | value '~' value ',' randomActuals
                    ;
arrayIndex          : INTEGERVAL
                    | variable
                    ;
gridtype            : TRIANGULAR
                    | SQUARE
                    | HEXAGONAL
                    ;
%%
private Yylex lexer;
private int lineno = 0;

private int yylex () {
  int yyl_return = -1;
  try {
    yylval = new CalVal(0);
    yyl_return = lexer.yylex();
  }
  catch (IOException e) {
    System.err.println("IO error :"+e);
  }
  return yyl_return;
}


public void yyerror (String error) {
  System.err.println ("Error: " + error);
}


public Cal(Reader r) {
  lexer = new Yylex(r, this);
}

public static void main(String args[]) throws IOException {

  Cal yyparser;
  if ( args.length > 0 ) {
    // parse a file
    yyparser = new Cal(new FileReader(args[0]));
  }
  else {
    yyparser = new Cal(new InputStreamReader(System.in));
  }

  yyparser.yyparse();
}