%{
  import java.io.*;
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

%nonassoc IF ELSEIF ELSE
%left AND OR NOR XOR NAND
%left '-' '+' '.'
%left '*' '/' FLOORDIVIDE
%left DECREMENT INCREMENT
%right '=' '^' IS NOT        /* exponentiation        */
%right ADDEQUAL SUBTRACTEQUAL MULTIPLYEQUAL DIVIDEEQUAL MODULOEQUAL FLOOREQUAL
      
%%

program             : statements
                    ;
statements          : statement
                    | statement statements
                    ;
statement           : header
                    | expression
                    | variableStatement
                    | conditional
                    | iteration
                    | continuation
                    ;
header              : gridDefinition
                    | cellDefinition
                    ;
gridDefinition      : GRID VARIABLE IS GRIDSIZE
                    | VARIABLE IS gridtype
                    ;
cellDefinition      : CELLS HAVE VARIABLE
                    | CELLS HAVE LIFE
                    ;
variableStatement   : variableDeclaration
                    | variableDefinition
                    | functionDeclaration
                    | functionCall
                    ;
variableDeclaration : type VARIABLE 
                    | type '[' INTEGERVAL ']' VARIABLE
                    ;                   
variableDefinition  : type VARIABLE '=' expression
                    | VARIABLE '[' arrayIndex ']' '=' expression 
                    | VARIABLE '=' expression
                    | VARIABLE '=' functionCall
                    | VARIABLE assign expression 
                    | iterable '.' VARIABLE '=' expression
                    | iterable '.' VARIABLE '[' arrayIndex ']' '=' expression
                    | iterable '.' VARIABLE '[' arrayIndex ']' assign expression
                    | iterables '.' VARIABLE '=' expression
                    | iterables '.' VARIABLE '[' arrayIndex ']' '=' expression  
                    | iterables '.' VARIABLE '[' arrayIndex ']' assign expression
                    ;
functionDeclaration : type VARIABLE '(' parameters ')' functionBlock
                    ;
functionCall        : VARIABLE '(' actuals ')'
                    | RANDOM '(' randomActuals')'
                    ;
expression          : expression '+' expression
                    | expression '-' expression
                    | expression '*' expression
                    | expression '/' expression
                    | expression '^' expression
                    | expression FLOORDIVIDE expression
                    | variable
                    | value
                    ;
conditional         : expression condition expression
                    | expression condition expression logic conditional
                    | NOT conditional
                    ;
iteration           : IF '(' conditional ')' block %prec IF
                    | IF '(' conditional ')' block elseif %prec ELSEIF 
                    | IF '(' conditional ')' block elseif ELSE block
                    | IF '(' conditional ')' block ELSE block
                    | FOR '(' forStatement ',' conditional ',' forStatement ')' block
                    | FOREACH '(' iterable IN iterables ')' block
                    | WHILE '(' conditional ')' block
                    ;
forStatement        : variableStatement
                    | expression
                    ;
elseif              : ELSEIF '(' conditional ')' block %prec IF
                    | ELSEIF '(' conditional ')' block elseif
                    ;
continuation        : CONTINUE
                    | BREAK
                    ;
variable            : VARIABLE
                    | VARIABLE INCREMENT
                    | VARIABLE DECREMENT
                    | iterable '.' VARIABLE
                    | iterables '.' VARIABLE
                    | iterable '.' VARIABLE '[' arrayIndex ']'
                    | iterables '.' VARIABLE '[' arrayIndex ']'
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
                    ;
functionBlock       : '|' statements return '|'
                    | return
                    ;
return              : RETURN value
                    | RETURN variable
                    ;
parameters          : 
                    | type VARIABLE
                    | type VARIABLE ',' parameters
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