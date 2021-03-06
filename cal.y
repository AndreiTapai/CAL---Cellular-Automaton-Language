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
program             : statements                                            { root = (StatementsNode)$1.obj; }
statements          : statement                                             { $$ = new CalVal(new StatementsNode((StatementNode)$1.obj)); } 
                    | statement statements  { 
                        $$ = new CalVal(new StatementsNode((StatementNode)$1.obj, ((StatementsNode)$2.obj).statements));
                    } 
                    ;
statement           : headerStatement                                       { $$ = new CalVal(new StatementNode((HeaderStatementNode)$1.obj)); }
                    | variableStatement                                     { $$ = new CalVal(new StatementNode((VariableStatementNode)$1.obj)); }
                    | functionStatement                                     { $$ = new CalVal(new StatementNode((FunctionStatementNode)$1.obj)); }
                    | continuation                                          { $$ = new CalVal(new StatementNode($1.sval)); }
                    | expressionStatement                                   { $$ = new CalVal(new StatementNode((ExpressionStatementNode)$1.obj)); }
                    | iteration                                             { $$ = new CalVal(new StatementNode((IterationStatementNode)$1.obj)); }
                    ;
headerStatement     : gridDefinition                                        { $$ = new CalVal(new HeaderStatementNode((GridDefinitionNode)$1.obj)); }
                    | cellDefinition                                        { $$ = new CalVal(new HeaderStatementNode((CellDefinitionNode)$1.obj)); }
                    ;
variableStatement   : variableDeclaration                                   { $$ = new CalVal(new VariableStatementNode((VariableDeclarationNode)$1.obj)); }
                    | variableDefinition                                    { $$ = new CalVal(new VariableStatementNode((VariableDefinitionNode)$1.obj)); }
                    ;
functionStatement   : functionDeclaration                                   { $$ = new CalVal(new FunctionStatementNode((FunctionDeclarationNode)$1.obj)); }
                    | functionCall                                          { $$ = new CalVal(new FunctionStatementNode((FunctionCallNode)$1.obj)); }
                    ;
continuation        : CONTINUE                                              { $$ = new CalVal($1.sval); }
                    | BREAK                                                 { $$ = new CalVal($1.sval); }
                    ;
expressionStatement : variable                                              { $$ = new CalVal(new ExpressionStatementNode($1.sval)); }
                    | variable INCREMENT                                    { $$ = new CalVal(new ExpressionStatementNode($1.sval+$2.sval)); }
                    | variable DECREMENT                                    { $$ = new CalVal(new ExpressionStatementNode($1.sval+$2.sval)); }
                    ;
expression          : expression '+' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, "+", (ExpressionNode)$3.obj)); }
                    | expression '-' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, "-", (ExpressionNode)$3.obj)); }
                    | expression '*' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, "*", (ExpressionNode)$3.obj)); }
                    | expression '/' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, "/", (ExpressionNode)$3.obj)); }
                    | expression '^' expression                              { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, "^", (ExpressionNode)$3.obj)); }
                    | expression FLOORDIVIDE expression                      { $$ = new CalVal(new ExpressionNode((ExpressionNode)$1.obj, $2.sval, (ExpressionNode)$3.obj)); }
                    | variable                                               { $$ = new CalVal(new ExpressionNode($1.sval)); }
                    | variable INCREMENT                                     { $$ = new CalVal(new ExpressionNode($1.sval + $2.sval)); }
                    | variable DECREMENT                                     { $$ = new CalVal(new ExpressionNode($1.sval + $2.sval)); }
                    | value                                                  { $$ = new CalVal(new ExpressionNode($1.sval)); }
                    ;
conditional         : expression condition expression                        { $$ = new CalVal(new ConditionalNode((ExpressionNode)$1.obj, $2.sval, (ExpressionNode)$3.obj)); }
                    | expression condition expression logic conditional      { $$ = new CalVal(new ConditionalNode((ExpressionNode)$1.obj, $2.sval, (ExpressionNode)$3.obj, (LogicNode)$4.obj, (ConditionalNode)$5.obj)); }
                    | NOT conditional                                        { $$ = new CalVal(new ConditionalNode((ConditionalNode)$2.obj)); }
                    ;
iteration           : IF '(' conditional ')' block   %prec IF                                    { $$ = new CalVal(new IterationStatementNode($1.sval, (ConditionalNode)$3.obj, (BlockNode)$5.obj)); }       
                    | IF '(' conditional ')' block elseif  %prec IF                              { $$ = new CalVal(new IterationStatementNode((ConditionalNode)$3.obj, (BlockNode)$5.obj, $6.sval)); }
                    | IF '(' conditional ')' block elseif ELSE block %prec IF                    { $$ = new CalVal(new IterationStatementNode((ConditionalNode)$3.obj, (BlockNode)$5.obj, $6.sval, (BlockNode)$8.obj)); }
                    | IF '(' conditional ')' block ELSE block        %prec IF                    { $$ = new CalVal(new IterationStatementNode((ConditionalNode)$3.obj, (BlockNode)$5.obj, (BlockNode)$7.obj)); }
                    | FOR '(' forStatement ',' conditional ',' forStatement ')' block    { $$ = new CalVal(new IterationStatementNode($3.sval, (ConditionalNode)$5.obj, $7.sval, (BlockNode)$9.obj)); }
                    | FOREACH '(' iterable IN iterables ')' block                        { $$ = new CalVal(new IterationStatementNode("Cell "+ $3.sval + " : " + $5.sval, (BlockNode)$7.obj)); }
                    | WHILE '(' conditional ')' block                                    { $$ = new CalVal(new IterationStatementNode($1.sval, (ConditionalNode)$3.obj, (BlockNode)$5.obj)); }
                    ;
gridDefinition      : GRID VARIABLE IS GRIDSIZE                             { $$ = new CalVal(new GridDefinitionNode($2.sval, $4.sval)); }
                    ;
cellDefinition      : CELLS HAVE type VARIABLE                              { $$ = new CalVal(new CellDefinitionNode($3.sval, $4.sval)); }
                    | CELLS HAVE type LIFE                                  { $$ = new CalVal(new CellDefinitionNode($3.sval, $4.sval)); }
                    ;
variableDeclaration : type VARIABLE                                         { $$ = new CalVal(new VariableDeclarationNode($1.sval+" "+$2.sval+";\n")); }
                    | type '[' INTEGERVAL ']' VARIABLE                      { $$ = new CalVal(new VariableDeclarationNode($1.sval+"["+$3.ival+"] "+$5.sval+";\n")); }
                    ;                   
variableDefinition  : type VARIABLE '=' expression                          { $$ = new CalVal(new VariableDefinitionNode($1.sval+" "+$2.sval, "=" , (ExpressionNode)$4.obj)); }                  
                    | variable '=' expression                               { $$ = new CalVal(new VariableDefinitionNode($1.sval,"=",(ExpressionNode)$3.obj)); }
                    | variable '=' functionCall                             { $$ = new CalVal(new VariableDefinitionNode($1.sval,"=",(FunctionCallNode)$3.obj)); }
                    | variable assign expression                            { $$ = new CalVal(new VariableDefinitionNode($1.sval,$2.sval,(ExpressionNode)$3.obj)); }
                    ;
functionDeclaration : type VARIABLE '(' parameters ')' functionBlock        { $$ = new CalVal(new FunctionDeclarationNode($1.sval, $2.sval, (ParametersNode)$4.obj, (FunctionBlockNode)$6.obj)); }
                    ;
functionCall        : VARIABLE '(' actuals ')'                              { $$ = new CalVal(new FunctionCallNode($1.sval, $3.sval)); }
                    | VARIABLE'(' ')'                                       { $$ = new CalVal(new FunctionCallNode($1.sval, "")); }
                    | RANDOM '(' randomActuals')'                           { $$ = new CalVal(new FunctionCallNode($1.sval, $3.sval)); }
                    ;
forStatement        : variableStatement                                     { $$ = new CalVal(((VariableStatementNode)$1.obj).toJavaExpression()); }
                    | variable INCREMENT                                    { $$ = new CalVal($1.sval+$2.sval); }
                    | variable DECREMENT                                    { $$ = new CalVal($1.sval+$2.sval); }
                    ;
elseif              : ELSEIF '(' conditional ')' block %prec IF             { $$ = new CalVal($1.sval.replace("-", " ")+"("+((ConditionalNode)$3.obj).toJava()+")"+((BlockNode)$5.obj).toJava()); }
                    | ELSEIF '(' conditional ')' block elseif  %prec IF             { $$ = new CalVal($1.sval.replace("-", " ")+"("+((ConditionalNode)$3.obj).toJava()+")"+((BlockNode)$5.obj).toJava()+$6.sval); }
                    ;
variable            : VARIABLE                                              { $$ = new CalVal($1.sval); }
                    | CELL                                                  { $$ = new CalVal($1.sval); }
                    | CELL '.' LIFE                                         { $$ = new CalVal($1.sval + "." + $3.sval); }
                    | CELL'.' VARIABLE                                      { $$ = new CalVal($1.sval + "." + $3.sval); }
                    | CELLS '.' LIFE                                        { $$ = new CalVal($1.sval + "." + $3.sval); }
                    | CELLS '.' VARIABLE                                    { $$ = new CalVal($1.sval + "." + $3.sval); }
                    | NEIGHBOR '.' LIFE                                     { $$ = new CalVal($1.sval + "." + $3.sval); }
                    | NEIGHBOR '.' VARIABLE                                 { $$ = new CalVal($1.sval + "." + $3.sval); }
                    | NEIGHBORS '.' LIFE                                    { $$ = new CalVal($1.sval + "." + $3.sval); }
                    | NEIGHBORS '.' VARIABLE                                { $$ = new CalVal($1.sval + "." + $3.sval); }
                    | CELL '.' VARIABLE '[' arrayIndex ']'                  { $$ = new CalVal($1.sval + "." + $3.sval + "[" + $5.sval + "]"); }
                    | NEIGHBOR '.' VARIABLE '[' arrayIndex ']'              { $$ = new CalVal($1.sval + "." + $3.sval + "[" + $5.sval + "]"); }
                    | CELLS '.' VARIABLE '[' arrayIndex ']'                 { $$ = new CalVal($1.sval + "." + $3.sval + "[" + $5.sval + "]"); }
                    | NEIGHBORS '.' VARIABLE '[' arrayIndex ']'             { $$ = new CalVal($1.sval + "." + $3.sval + "[" + $5.sval + "]"); }
                    | VARIABLE '[' arrayIndex ']'                           { $$ = new CalVal($1.sval + "[" + $3.sval + "]"); }
                    ;
iterable            : CELL                                                  { $$ = new CalVal($1.sval); }
                    | NEIGHBOR                                              { $$ = new CalVal($1.sval); }
                    ;
iterables           : CELLS                                                 { $$ = new CalVal($1.sval); }
                    | NEIGHBORS                                             { $$ = new CalVal("cell." + $1.sval); }
                    ;
type                : INTEGER                                               { $$ = new CalVal($1.sval); }
                    | FLOAT                                                 { $$ = new CalVal($1.sval); }
                    | BOOLEAN                                               { $$ = new CalVal($1.sval); }
                    | CHARACTER                                             { $$ = new CalVal($1.sval); }
                    | STRING                                                { $$ = new CalVal($1.sval); }
                    | VOID                                                  { $$ = new CalVal($1.sval); }
                    | NEIGHBOR                                              { $$ = new CalVal($1.sval); }
                    ;
value               : INTEGERVAL                                            { $$ = new CalVal(String.valueOf($1.ival)); }
                    | FLOATVAL                                              { $$ = new CalVal(String.valueOf($1.dval)); }
                    | TRUE                                                  { $$ = new CalVal($1.sval); }
                    | FALSE                                                 { $$ = new CalVal($1.sval); }
                    | CHARACTERVAL                                          { $$ = new CalVal($1.sval); }
                    | STRINGVAL                                             { $$ = new CalVal($1.sval); }
                    ;
assign              : ADDEQUAL                                              { $$ = new CalVal($1.sval); }
                    | SUBTRACTEQUAL                                         { $$ = new CalVal($1.sval); }
                    | MULTIPLYEQUAL                                         { $$ = new CalVal($1.sval); }
                    | DIVIDEEQUAL                                           { $$ = new CalVal($1.sval); }
                    | MODULOEQUAL                                           { $$ = new CalVal($1.sval); }
                    | FLOOREQUAL                                            { $$ = new CalVal($1.sval); }
                    ;
condition           : EQUALS                                                { $$ = new CalVal($1.sval); }
                    | GREATER                                               { $$ = new CalVal($1.sval); }
                    | GREATEREQUALS                                         { $$ = new CalVal($1.sval); }
                    | LESS                                                  { $$ = new CalVal($1.sval); }
                    | LESSEQUALS                                            { $$ = new CalVal($1.sval); }
                    | NOTEQUALS                                             { $$ = new CalVal($1.sval); }
                    ;
logic               : AND                                                   { $$ = new CalVal(new LogicNode($1.sval)); }
                    | OR                                                    { $$ = new CalVal(new LogicNode($1.sval)); }
                    | NOR                                                   { $$ = new CalVal(new LogicNode($1.sval)); }
                    | NAND                                                  { $$ = new CalVal(new LogicNode($1.sval)); }
                    | XOR                                                   { $$ = new CalVal(new LogicNode($1.sval)); }
                    ;
block               : '|' statements '|'            { $$ = new CalVal(new BlockNode((StatementsNode)$2.obj)); }
                    | statement                     { $$ = new CalVal(new BlockNode((StatementNode)$1.obj)); }
                    | '|' '|'                       { $$ = new CalVal(new BlockNode()); }
                    ;
functionBlock       : '|' statements return '|'     { $$ = new CalVal(new FunctionBlockNode((StatementsNode)$2.obj, (ReturnsNode)$3.obj)); }
                    | '|' return '|'                { $$ = new CalVal(new FunctionBlockNode((ReturnsNode)$2.obj)); }
                    | '|' statements'|'             { $$ = new CalVal(new FunctionBlockNode((StatementsNode)$2.obj)); }
                    | '|' '|'                       { $$ = new CalVal(new FunctionBlockNode()); }
                    ;
return              : RETURN value                  { $$ = new CalVal(new ReturnsNode(Double.parseDouble($2.sval))); } 
                    | RETURN variable               { $$ = new CalVal(new ReturnsNode($2.sval)); }
                    ;
parameters          :                               { $$ = new CalVal(new ParametersNode()); }
                    | type VARIABLE                 { $$ = new CalVal(new ParametersNode($1.sval, $2.sval)); }
                    | type VARIABLE ',' parameters  { $$ = new CalVal(new ParametersNode($1.sval, $2.sval, ((ParametersNode)$4.obj).params)); }
                    | CELL VARIABLE                 { $$ = new CalVal(new ParametersNode($1.sval.replace("c", "C"), "cell")); }
                    ;
actuals             : variable                      { $$ = (CalVal)$1; }
                    | variable ',' actuals          { $$ = new CalVal($1.sval+","+$3.sval); }
                    ;
randomActuals       : 
                    | value                                                 { $$ = (CalVal)$1; }
                    | value ',' randomActuals                               { $$ = new CalVal($1.sval +','+$3.sval); }
                    | value '~' value                                       { $$ = new CalVal($1.sval +'~'+$3.sval); }
                    | value '~' value ',' randomActuals                     { $$ = new CalVal($1.sval +'~'+$3.sval+','+$5.sval); }
                    ;
arrayIndex          : INTEGERVAL                                            { $$ = new CalVal(String.valueOf($1.ival)); }
                    | VARIABLE                                              { $$ = new CalVal($1.sval); }
                    ;
%%
private Yylex lexer;
private int lineno = 0;
private static StatementsNode root;

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
  try{
     String className = args[0].split("\\.")[0]+"CAL";
     String firstChar = String.valueOf(className.charAt(0)).toUpperCase();
     String imports = "import java.util.ArrayList;\n"+
                       "import java.util.Random;\n";
     className = firstChar + className.substring(1, className.length());

     String boilerClass = 
     "public class Cell {\n" +
     "/* The below are standard attributes of all Cells: x, y, life, and neighbors */\n" +
     "public int x;\n" +
     "public int y;\n" +
     "public boolean life;\n" +
     "public ArrayList<Cell> neighbors = new ArrayList<Cell>();\n" +

     "public Cell(int x, int y) {\n" +
          "this.x = x;\n" +
          "this.y = y;\n" +
          "/* Random function inserted according to what is needed */\n" +
          "Random r = new Random();\n" +
          "life = r.nextBoolean();\n" +
     "}\n" +

     "// standard function in all Cell classes\n" +
     "public void addNeighbors(Cell c) {\n" +
          "this.neighbors.add(c);\n" +
     "}\n" +

     "}";

     String boilerMain = 
     "public ArrayList<Cell> cells = new ArrayList<Cell>();\n"+

     "public "+ className+"() {\n"+
          "for (int i = 0; i < gridgx; i++)\n"+
               "for (int j = 0; j < gridgy; j++) {\n"+
                    "cells.add(new Cell(i, j));\n"+
               "}\n"+
          "for (Cell cell : cells) {\n"+
              "if (cell.x > 0)\n"+
                    "cell.addNeighbors(getNeighbors(cell.x - 1, cell.y));\n"+
               "if (cell.y > 0)\n"+
                    "cell.addNeighbors(getNeighbors(cell.x, cell.y - 1));\n"+
               "if (cell.x < gridgx - 1)\n"+
                    "cell.addNeighbors(getNeighbors(cell.x + 1, cell.y));\n"+
               "if (cell.y < gridgy - 1)\n"+
                    "cell.addNeighbors(getNeighbors(cell.x, cell.y + 1));\n"+
               "if (cell.x > 0 && cell.y > 0)\n"+
                    "cell.addNeighbors(getNeighbors(cell.x - 1, cell.y - 1));\n"+
               "if (cell.x < gridgx - 1 && cell.y > 0)\n"+
                    "cell.addNeighbors(getNeighbors(cell.x + 1, cell.y - 1));\n"+
               "if (cell.x > 0 && cell.y < gridgy - 1)\n"+                            
                   " cell.addNeighbors(getNeighbors(cell.x - 1, cell.y + 1));\n"+                       
               "if (cell.x < gridgx - 1 && cell.y < gridgy - 1)\n"+
                    "cell.addNeighbors(getNeighbors(cell.x + 1, cell.y + 1));\n"+
          "}\n"+
     "}\n"+
     "public Cell getNeighbors(int x, int y) {\n"+
          "for (int i = 0; i < cells.size(); i++)\n"+
               "if (cells.get(i).x == x && cells.get(i).y == y) {\n"+                                
                                "System.out.println(\"Neighbor: \" + cells.get(i).x + \", \" + cells.get(i).y);\n"+
                    "return (Cell) cells.get(i);\n"+
               "}\n"+
          "return null; // Shouldn't be reachable\n"+
     "}\n";
    PrintWriter pw = new PrintWriter(className+".java");
    pw.println(imports);
    pw.println("public class " + className +" {");
    pw.print(boilerMain);
    pw.print(root.toJava());
    pw.print(boilerClass);
    pw.println("}");
    pw.close();
    Utils.format(className+".java");
    }
    catch(IOException e){
    System.out.println(e);
    }
}