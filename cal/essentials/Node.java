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
        else {
        	return nodeName;
        }
	}
}