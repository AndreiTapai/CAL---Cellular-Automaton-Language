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

program             : statements                                            { $$ = new CalVal(new StatementsNode()); } /* should be ProgramNode right? and do we need to declare this is root of AST? */
                    ;
statements          : statement                                             { $$ = new CalVal(new StatementsNode((StatementNode)$1.obj)); } 
                    | statement statements                                  { $$ = new CalVal(new StatementsNode((StatementNode)$1.obj, (StatementsNode)$2.obj)); }
                    ;
statement           : headerStatement                                       { $$ = new CalVal(new StatementNode((HeaderStatementNode)$1.obj)); }
                    | variableStatement                                     { $$ = new CalVal(new StatementNode((VariableStatementNode)$1.obj)); }
                    | functionStatement                                     { $$ = new CalVal(new StatementNode((FunctionStatement)$1.obj)); }
                    | continuation                                          { $$ = new CalVal(new StatementNode((ContinuationNode)$1.obj)); }
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
continuation        : CONTINUE                                              { $$ = new CalVal(new ContinuationNode($1.sval)); }
                    | BREAK                                                 { $$ = new CalVal(new ContinuationNode($1.sval)); }
                    ;
expressionStatement : variable                                              { $$ = new CalVal(new ExpressionStatementNode((VariableNode)$1.obj)); }
                    | variable INCREMENT                                    { $$ = new CalVal(new ExpressionStatementNode((VariableNode)$1.obj, $2.sval)); }
                    | variable DECREMENT                                    { $$ = new CalVal(new ExpressionStatementNode((VariableNode)$1.obj, $2.sval)); }
                    | value                                                 { $$ = new CalVal(new ExpressionStatementNode((ValueNode)$1.obj)); }
                    ;
expression          : expression '+' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2, (ExpressionNode)$3.obj)); }
                    | expression '-' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2, (ExpressionNode)$3.obj)); }
                    | expression '*' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2, (ExpressionNode)$3.obj)); }
                    | expression '/' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2, (ExpressionNode)$3.obj)); }
                    | expression '^' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2, (ExpressionNode)$3.obj)); }
                    | expression FLOORDIVIDE expression                      { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2.sval, (ExpressionNode)$3.obj)); }
                    | variable                                               { $$ = new CalVal(new ExpressionNode((VariableNode)$1.obj)); }
                    | variable INCREMENT                                     { $$ = new CalVal(new ExpressionNode((VariableNode)$1.obj, $2.sval)); }
                    | variable DECREMENT                                     { $$ = new CalVal(new ExpressionNode((VariableNode)$1.obj, $2.sval)); }
                    | variable '[' arrayIndex ']'                            { $$ = new CalVal(new ExpressionNode((VariableNode)$1.obj, (ArrayIndexNode)$3.obj)); }
                    | value                                                  { $$ = new CalVal(new ExpressionNode((ValueNode)$1.obj)); }
                    ;
conditional         : expression condition expression                        { $$ = new CalVal(new ConditionalNode((ExpressionNode)$1.obj, (ConditionNode)$2.obj, (ExpressionNode)$3.obj)); }
                    | expression condition expression logic conditional      { $$ = new CalVal(new ConditionalNode((ExpressionNode) $1.obj, (ConditionNode)$2.obj, (ExpressionNode)$3.obj, (LogicNode)$4.obj, (ConditionalNode)$5.obj)); }
                    | NOT conditional                                        { $$ = new CalVal(new ConditionalNode((ConditionalNode)$2.obj)); }
                    ;
iteration           : IF '(' conditional ')' block                                       { $$ = new CalVal(new IterationNode((ConditionalNode)$3.obj, (BlockNode)$5.obj)); }       
                    | IF '(' conditional ')' block elseif                                { $$ = new CalVal(new IterationNode((ConditionalNode)$3.obj, (BlockNode)$5.obj, (ElseIfNode)$6.obj)); }
                    | IF '(' conditional ')' block elseif ELSE block                     { $$ = new CalVal(new IterationNode((ConditionalNode)$3.obj, (BlockNode)$5.obj, (ElseIfNode)$6.obj, (BlockNode)$8.obj)); }
                    | IF '(' conditional ')' block ELSE block                            { $$ = new CalVal(new IterationNode((ConditionalNode)$3.obj, (BlockNode)$5.obj, (BlockNode)$7.obj)); }
                    | FOR '(' forStatement ',' conditional ',' forStatement ')' block    { $$ = new CalVal(new IterationNode((ForStatementNode)$3.obj, (ConditionalNode)$5.obj, (ForStatementNode)$7.obj, (BlockNode)$9.obj)); }
                    | FOREACH '(' iterable IN iterables ')' block                        { $$ = new CalVal(new IterationNode((IterableNode)$3.obj, (IterablesNode)$5.obj, (BlockNode)$7.obj)); }
                    | WHILE '(' conditional ')' block                                    { $$ = new CalVal(new IterationNode((ConditionalNode)$3.obj, (BlockNode)$5.obj)); }
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
functionDeclaration : type VARIABLE '(' parameters ')' functionBlock        { $$ = new CalVal(new FunctionDeclarationNode($1.sval, $2.sval, (ParametersNode)$4.obj, (FunctionBlockNode)$6.obj)); }
                    ;
functionCall        : VARIABLE '(' actuals ')'                              { $$ = new CalVal(new FunctionCallNode($1.sval, (ActualsNode)$3.obj)); }
                    | RANDOM '(' randomActuals')'                           { $$ = new CalVal(new FunctionCallNode((RandomActualsNode)$3.obj)); }
                    ;
forStatement        : variableStatement                                     { $$ = new CalVal(new ForStatementNode((VariableStatementNode)$1.obj)); }
                    | variable INCREMENT                                    { $$ = new CalVal(new ForStatementNode((VariableNode)$1.obj, $2.sval)); }
                    | variable DECREMENT                                    { $$ = new CalVal(new ForStatementNode((VariableNode)$1.obj, $2.sval)); }
                    ;
elseif              : ELSEIF '(' conditional ')' block %prec IF                { $$ = new CalVal(new ElseIfNode((ConditionalNode)$3.obj, (BlockNode)$5.obj)); }
                    | ELSEIF '(' conditional ')' block elseif                  { $$ = new CalVal(new ElseIfNode(ConditionalNode)$3.obj, (BlockNode)$5.obj, (ElseIfNode)$6.obj)); }
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
logic               : AND                      { $$ = new CalVal(new LogicNode($1.sval)); }
                    | OR                       { $$ = new CalVal(new LogicNode($1.sval)); }
                    | NOR                      { $$ = new CalVal(new LogicNode($1.sval)); }
                    | NAND                     { $$ = new CalVal(new LogicNode($1.sval)); }
                    | XOR                      { $$ = new CalVal(new LogicNode($1.sval)); }
                    ;
block               : '|' statements '|'           { $$ = new CalVal(new BlockNode((StatementsNode)$2.obj)); }
                    | statement                    { $$ = new CalVal(new BlockNode((StatementNode)$1.obj)); }
                    | '|' '|'                      { $$ = new CalVal(new BlockNode()); }
                    ;
functionBlock       : '|' statements return '|'     { $$ = new CalVal(new FunctionBlockNode((StatementsNode)$2.obj, (ReturnsNode)$3.obj)); }
                    | '|' return '|'                { $$ = new CalVal(new FunctionBlockNode((StatementsNode)$2.obj)); }
                    | '|' statements'|'             { $$ = new CalVal(new FunctionBlockNode((StatementsNode)$2.obj)); }
                    | '|' '|'                       { $$ = new CalVal(new FunctionBlockNode()); }
                    ;
return              : RETURN value                  { $$ = new CalVal(new ReturnsNode($2.dval)); } 
                    | RETURN variable               { $$ = new CalVal(new ReturnsNode($2.sval)); }
                    ;
parameters          :                               { $$ = new CalVal(new ParametersNode()); }
                    | type VARIABLE                 { $$ = new CalVal(new ParametersNode($1.sval, $2.sval)); }
                    | type VARIABLE ',' parameters  { $$ = new CalVal(new ParametersNode($1.sval, $2.sval, $3.sval)); }
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