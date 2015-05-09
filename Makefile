# only works with the Java extension of yacc: 
# byacc/j from http://troi.lincom-asg.com/~rjamison/byacc/

JFLEX  = bin/jflex 
BYACCJ = bin/byacc -J
JAVAC  = javac

# targets:

all: Cal.class

run: Cal.class
	java Cal

build: clean Cal.class

clean:
	rm -f *~ *.class *.java

Cal.class: Yylex.java Cal.java
	$(JAVAC) Cal.java

Yylex.java: cal.flex
	$(JFLEX) cal.flex

Cal.java: cal.y
	$(BYACCJ) -Jclass=Cal cal.y
