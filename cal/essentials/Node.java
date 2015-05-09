package cal.essentials;

public class Node{
	
	public String nodeName;
	
	public Node(String s){
		nodeName = s;
	}

	public String toString(){
        if (nodeName == "IF"){
            return "if";
        }
        else if (nodeName == "ELSEIF"){
            return "else if";
        }
        else if (nodeName == "ELSE"){
            return "else";
        }
        else if (nodeName == "FOR"){
            return "for";
        }
        else if (nodeName == "FOREACH"){
            return "for";
        }
        else if (nodeName == "WHILE"){
            return "while";
 		}
        else if (nodeName == "IN"){
        	return ":";
        }
                else if (nodeName == "EQUALS"){
        	return "==";
        }
        else if (nodeName == "GREATER"){
        	return ">";
        }
        else if (nodeName == "GREATEREQUALS"){
        	return ">=";
        }
        else if (nodeName == "LESS"){
        	return "<";
        }
        else if (nodeName == "LESSEQUALS"){
        	return "<=";
        }
        else if (nodeName == "NOTEQUALS"){
        	return "!=";
        }
        else if (nodeName == "NOT"){
        	return "!";
        }
        else if (nodeName == "AND"){
        	return "&&";
        }
        else if (nodeName == "OR"){
        	return "||";
        }
        else if (nodeName == "XOR"){
        	return "^";
        }
        /*NAND
         * NOR
         */
        else if (nodeName == "CONTINUE"){
        	return "continue";
        }
        else if (nodeName == "BREAK"){
        	return "break";
        }
        else {
        	return nodeName;
        }
	}
}