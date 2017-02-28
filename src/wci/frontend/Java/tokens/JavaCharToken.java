package wci.frontend.Java.tokens;

import wci.frontend.Source;
import wci.frontend.Token;
import static wci.frontend.Source.EOL;
import static wci.frontend.Source.EOF;
import static wci.frontend.Java.JavaTokenType.*;
import static wci.frontend.Java.JavaErrorCode.*;
public class JavaCharToken extends Token {

	public JavaCharToken(Source source) throws Exception {
		super(source);
		
		// TODO Auto-generated constructor stub
	}
	

protected void extract()
	throws Exception{
	
		StringBuilder textBuffer = new StringBuilder();
		StringBuilder valueBuffer = new StringBuilder();

		char currentChar = nextChar();
		
	do {	
			// Replace any whitespace character with a blank.
    		if (Character.isWhitespace(currentChar)) {
    			while (Character.isWhitespace(currentChar)){
    				currentChar = ' ';
    				currentChar = nextChar();
    				
    			}
    		}
			//Read initial quote '
			if (currentChar =='\''){
				//System.out.println("Checked");
				currentChar = nextChar();
			}
			// Not sure why this append does not work inside the initial quote conditions
			textBuffer.append('\'');
			valueBuffer.append('\'');
        	if (currentChar =='\\'){
        		while((currentChar == '\\') && (peekChar() =='\\')){
            		textBuffer.append("\\\\");
            		valueBuffer.append('\\');
            		currentChar = nextChar();
            		currentChar = nextChar();
            	}
        	// check \t case
            	while ((currentChar == '\\') && (peekChar() == 't')){
            		textBuffer.append("\\t");
            		valueBuffer.append('\t');
            		currentChar = nextChar();
            		currentChar = nextChar(); // consume '\t' character
            	}
        	 //Check \n case
            	while((currentChar == '\\') && (peekChar() == 'n')){
            		textBuffer.append("\\n");
            		valueBuffer.append('\n');
            		currentChar = nextChar();
            		currentChar = nextChar();// consume '\n' character 
            	}
        	// Check \' case make sure it does not recognize as '\' and ' for '/''
            	while((currentChar == '\\') && (peekChar() =='\'')){
            		textBuffer.append("\\'");
            		valueBuffer.append('\'');
            		currentChar = nextChar();
            		System.out.println(currentChar);
            		currentChar = nextChar();
            	}
            }
         // Read normal character 
        	if ((currentChar != '\\') && (currentChar != EOL) &&(currentChar !='\'')) {
        		textBuffer.append(currentChar);
        		valueBuffer.append(currentChar);
        		//System.out.println(currentChar); // check append the right char
        		currentChar = nextChar();  // consume character
        	}
         // Read the close quote '
         			if ((currentChar == '\'') && (Character.isWhitespace(peekChar())))  {
         		          type = CHAR;
         		          textBuffer.append('\'');
         		          valueBuffer.append('\'');
         		          text = textBuffer.toString();
         		          value = valueBuffer.toString();
         		          //System.out.println(value);
         		          currentChar = nextChar();
         		          break;
         		      }
         		      else {
         		          type = ERROR;
         		          value = UNEXPECTED_EOF;
         		          break; 
         		      }
		}while(currentChar != EOL);// to check if comes to the end of line 
	}	 
}	
