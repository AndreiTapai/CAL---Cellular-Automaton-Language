%{
import java.io.*;
import cal.essentials.*;
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
program             : statements                                            { root = new AbstractNode((StatementsNode)$1.obj); }
statements          : statement                                             { $$ = new CalVal(new StatementsNode((StatementNode)$1.obj)); } 
                    | statement statements  { 
                        $$ = new CalVal(new StatementsNode((StatementNode)$1.obj, ((StatementsNode)$2.obj).statements));
                    } 
                    ;
statement           : headerStatement                                       { $$ = new CalVal(new StatementNode((HeaderStatementNode)$1.obj)); }
                    | variableStatement                                     { $$ = new CalVal(new StatementNode((VariableStatementNode)$1.obj)); }
                    | functionStatement                                     { $$ = new CalVal(new StatementNode((FunctionStatementNode)$1.obj)); }
                    | continuation                                          { $$ = new CalVal(new StatementNode($1)); }
                    | expressionStatement                                   { $$ = new CalVal(new StatementNode((ExpressionStatementNode)$1.obj)); }
                    | iteration                                             { $$ = new CalVal(new StatementNode((IterationNode)$1.obj)); }
                    ;
headerStatement     : gridDefinition                                        { $$ = new CalVal(new HeaderStatementNode((GridDefinitionNode)$1.obj)); }
                    | cellDefinition                                        { $$ = new CalVal(new HeaderStatementNode((CellDefinitionNode)$1.obj)); }
                    ;
variableStatement   : variableDeclaration                                   { $$ = new CalVal(new VariableStatementNode((VariableDeclarationNode)$1.obj)); }
                    | variableDefinition                                    { $$ = new CalVal(new VariableStatementNode((VaraibleDefinitionNode)$1.obj)); }
                    ;
functionStatement   : functionDeclaration                                   { $$ = new CalVal(new FunctionStatementNode((FunctionDeclarationNode)$1.obj)); }
                    | functionCall                                          { $$ = new CalVal(new FunctionStatementNode((FunctionCallNode)$1.obj)); }
                    ;
continuation        : CONTINUE                                              { $$ = $1.sval; }
                    | BREAK                                                 { $$ = $1.sval; }
                    ;
expressionStatement : variable                                              { $$ = new CalVal(new ExpressionStatementNode($1)); }
                    | variable INCREMENT                                    { $$ = new CalVal(new ExpressionStatementNode($1, $2.sval)); }
                    | variable DECREMENT                                    { $$ = new CalVal(new ExpressionStatementNode($1, $2.sval)); }
                    | value                                                 { $$ = new CalVal(new ExpressionStatementNode($1)); }
                    ;
expression          : expression '+' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2, (ExpressionNode)$3.obj)); }
                    | expression '-' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2, (ExpressionNode)$3.obj)); }
                    | expression '*' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2, (ExpressionNode)$3.obj)); }
                    | expression '/' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2, (ExpressionNode)$3.obj)); }
                    | expression '^' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2, (ExpressionNode)$3.obj)); }
                    | expression FLOORDIVIDE expression                      { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2.sval, (ExpressionNode)$3.obj)); }
                    | variable                                               { $$ = new CalVal(new ExpressionNode($1)); }
                    | variable INCREMENT                                     { $$ = new CalVal(new ExpressionNode($1, $2.sval)); }
                    | variable DECREMENT                                     { $$ = new CalVal(new ExpressionNode($1, $2.sval)); }
                    | variable '[' INTEGERVAL ']'                            { $$ = new CalVal(new ExpressionNode($1+$2+$3.ival+$4)); }
                    | value                                                  { $$ = new CalVal(new ExpressionNode($1)); }
                    ;
conditional         : expression condition expression                        { $$ = new CalVal(new ConditionalNode((ExpressionNode)$1.obj, (ConditionNode)$2.obj, (ExpressionNode)$3.obj)); }
                    | expression condition expression logic conditional      { $$ = new CalVal(new ConditionalNode((ExpressionNode) $1.obj, (ConditionNode)$2.obj, (ExpressionNode)$3.obj, (LogicNode)$4.obj, (ConditionalNode)$5.obj)); }
                    | NOT conditional                                        { $$ = new CalVal(new ConditionalNode((ConditionalNode)$2.obj)); }
                    ;
iteration           : IF '(' conditional ')' block                                       { $$ = new CalVal(new IterationStatementNode((ConditionalNode)$3.obj, (BlockNode)$5.obj)); }       
                    | IF '(' conditional ')' block elseif                                { $$ = new CalVal(new IterationStatementNode((ConditionalNode)$3.obj, (BlockNode)$5.obj, (ElseIfNode)$6.obj)); }
                    | IF '(' conditional ')' block elseif ELSE block                     { $$ = new CalVal(new IterationStatementNode((ConditionalNode)$3.obj, (BlockNode)$5.obj, (ElseIfNode)$6.obj, (BlockNode)$8.obj)); }
                    | IF '(' conditional ')' block ELSE block                            { $$ = new CalVal(new IterationStatementNode((ConditionalNode)$3.obj, (BlockNode)$5.obj, (BlockNode)$7.obj)); }
                    | FOR '(' forStatement ',' conditional ',' forStatement ')' block    { $$ = new CalVal(new IterationStatementNode((ForStatementNode)$3.obj, (ConditionalNode)$5.obj, (ForStatementNode)$7.obj, (BlockNode)$9.obj)); }
                    | FOREACH '(' iterable IN iterables ')' block                        { $$ = new CalVal(new IterationStatementNode((IterableNode)$3.obj, (IterablesNode)$5.obj, (BlockNode)$7.obj)); }
                    | WHILE '(' conditional ')' block                                    { $$ = new CalVal(new IterationStatementNode((ConditionalNode)$3.obj, (BlockNode)$5.obj)); }
                    ;
gridDefinition      : GRID VARIABLE IS GRIDSIZE                             { $$ = new CalVal(new GridDefinitionNode($2.sval, $4.sval, true)); }
                    | VARIABLE IS gridtype                                  { $$ = new CalVal(new GridDefinitionNode($2.sval, $3, false)); }
                    ;
cellDefinition      : CELLS HAVE VARIABLE                                   { $$ = new CalVal(new CellDefinitionNode($3.sval)); }
                    | CELLS HAVE LIFE                                       { $$ = new CalVal(new CellDefinitionNode($3.sval)); }
                    ;
variableDeclaration : type VARIABLE                                         { $$ = new CalVal(new VariableDeclarationNode($1+$2.sval)); }
                    | type '[' INTEGERVAL ']' VARIABLE                      { $$ = new CalVal(new VariableDeclarationNode($1+$2+$3.sval+$4+$5.sval)); }
                    ;                   
variableDefinition  : type VARIABLE '=' expression                          { $$ = new CalVal(new VariableDefitionNode($1,$2.sval,$3, (ExpressionNode)$4.obj)); }                  
                    | VARIABLE '[' INTEGERVAL ']' '=' expression            { $$ = new CalVal(new VariableDefitionNode($1+$2+$3+$4,$5,(ExpressionNode)$6.obj)); }
                    | variable '=' expression                               { $$ = new CalVal(new VariableDefitionNode($1,$2,(ExpressionNode)$3.obj)); }
                    | variable '=' functionCall                             { $$ = new CalVal(new VariableDefitionNode($1,$2,(FunctionCallNode)$3.obj)); }
                    | variable assign expression                            { $$ = new CalVal(new VariableDefitionNode($1,$2,(ExpressionNode)$3.obj)); }
                    ;
functionDeclaration : type VARIABLE '(' parameters ')' functionBlock        { $$ = new CalVal(new FunctionDeclarationNode($1.sval, $2.sval, (ParametersNode)$4.obj, (FunctionBlockNode)$6.obj)); }
                    ;
functionCall        : VARIABLE '(' actuals ')'                              { $$ = new CalVal(new FunctionCallNode($1.sval, (ActualsNode)$3.obj)); }
                    | RANDOM '(' randomActuals')'                           { $$ = new CalVal(new FunctionCallNode((RandomActualsNode)$3.obj)); }
                    ;
forStatement        : variableStatement                                     { $$ = new CalVal(new ForStatementNode((VariableStatementNode)$1.obj)); }
                    | variable INCREMENT                                    { $$ = new CalVal(new ForStatementNode($1, $2.sval)); }
                    | variable DECREMENT                                    { $$ = new CalVal(new ForStatementNode($1, $2.sval)); }
                    ;
elseif              : ELSEIF '(' conditional ')' block %prec IF             { $$ = new CalVal(new ElseIfNode((ConditionalNode)$3.obj, (BlockNode)$5.obj)); }
                    | ELSEIF '(' conditional ')' block elseif               { $$ = new CalVal(new ElseIfNode((cConditionalNode)$3.obj, (BlockNode)$5.obj, (ElseIfNode)$6.obj)); }
                    ;
variable            : VARIABLE                                              { $$ = $1.sval; }
                    | CELL                                                  { $$ = $1.sval; }
                    | CELL '.' LIFE                                         { $$ = $1.sval + $2 + $3.sval; }
                    | CELL'.' VARIABLE                                      { $$ = $1.sval + $2 + $3.sval; }
                    | CELLS '.' LIFE                                        { $$ = $1.sval + $2 + $3.sval; }
                    | CELLS '.' VARIABLE                                    { $$ = $1.sval + $2 + $3.sval; }
                    | NEIGHBOR '.' LIFE                                     { $$ = $1.sval + $2 + $3.sval; }
                    | NEIGHBOR '.' VARIABLE                                 { $$ = $1.sval + $2 + $3.sval; }
                    | NEIGHBORS '.' LIFE                                    { $$ = $1.sval + $2 + $3.sval; }
                    | NEIGHBORS '.' VARIABLE                                { $$ = $1.sval + $2 + $3.sval; }
                    | CELL '.' VARIABLE '[' INTEGERVAL ']'                  { $$ = $1.sval + $2 + $3.sval + $4 + $5 + $6; }
                    | NEIGHBOR '.' VARIABLE '[' INTEGERVAL ']'              { $$ = $1.sval + $2 + $3.sval + $4 + $5 + $6; }
                    | CELLS '.' VARIABLE '[' INTEGERVAL ']'                 { $$ = $1.sval + $2 + $3.sval + $4 + $5 + $6; }
                    | NEIGHBORS '.' VARIABLE '[' INTEGERVAL ']'             { $$ = $1.sval + $2 + $3.sval + $4 + $5 + $6; }
                    ;
iterable            : CELL
                    | NEIGHBOR
                    ;
iterables           : CELLS
                    | NEIGHBORS
                    ;
type                : INTEGER                                               { $$ = $1.sval; }
                    | FLOAT                                                 { $$ = $1.sval; }
                    | BOOLEAN                                               { $$ = $1.sval; }
                    | CHARACTER                                             { $$ = $1.sval; }
                    | STRING                                                { $$ = $1.sval; }
                    | VOID                                                  { $$ = $1.sval; }
                    | NEIGHBOR                                              { $$ = $1.sval; }
                    ;
value               : INTEGERVAL                                            { $$ = $1.ival; }
                    | FLOATVAL                                              { $$ = $1.dval; }
                    | TRUE                                                  { $$ = $1.sval; }
                    | FALSE                                                 { $$ = $1.sval; }
                    | CHARACTERVAL                                          { $$ = $1.sval; }
                    | STRINGVAL                                             { $$ = $1.sval; }
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
functionBlock       : '|' statements return '|'     { $$ = new CalVal(new FunctionBlockNode((StatementsNode)$2.obj, (ReturnsNode)$3.obj)); }
                    | '|' return '|'                { $$ = new CalVal(new FunctionBlockNode((ReturnsNode)$2.obj)); }
                    | '|' statements'|'             { $$ = new CalVal(new FunctionBlockNode((StatementsNode)$2.obj)); }
                    | '|' '|'                       { $$ = new CalVal(new FunctionBlockNode()); }
                    ;
return              : RETURN value                  { $$ = new CalVal(new ReturnsNode($2.dval)); } 
                    | RETURN variable               { $$ = new CalVal(new ReturnsNode($2.sval)); }
                    ;
parameters          :                               { $$ = new CalVal(new ParametersNode()); }
                    | type VARIABLE                 { $$ = new CalVal(new ParametersNode($1.sval, $2.sval)); }
                    | type VARIABLE ',' parameters  { $$ = new CalVal(new ParametersNode($1.sval, $2.sval, ((ParametersNode)$4.obj).params)); }
                    | CELL VARIABLE                 { $$ = new CalVal(new ParametersNode($1.sval, $2.sval)); }
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
gridtype            : TRIANGULAR
                    | SQUARE
                    | HEXAGONAL
                    ;
%%
private Yylex lexer;
private int lineno = 0;
private AbstractNode root;

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