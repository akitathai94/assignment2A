package wci.frontend.Java.tokens;

import wci.frontend.*;
import wci.frontend.Java.*;

import static wci.frontend.Source.EOL;
import static wci.frontend.Source.EOF;
import static wci.frontend.Java.JavaTokenType.*;
import static wci.frontend.Java.JavaErrorCode.*;

/**
 * <h1>JavaStringToken</h1>
 *
 * <p> Java string tokens.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class JavaStringToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaStringToken(Source source)
        throws Exception
    {
        super(source);
    }

    /**
     * Extract a Java string token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract()
        throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();

        char currentChar = nextChar();  // consume initial quote
        textBuffer.append('\"');

        // Get string characters.
        do {
            // Replace any whitespace character with a blank.
            if (Character.isWhitespace(currentChar)) {
                currentChar = ' ';
            }
            // Check \" case
            if ((currentChar =='\\')){
            	while((currentChar == '\\') && (peekChar() =='\"')){
            		textBuffer.append("\\");
            		textBuffer.append('\"');
            		valueBuffer.append('\"');
            		currentChar = nextChar();
            		currentChar = nextChar();
            	}
            }
            //Check \t case
            if ((currentChar =='\\')){
            	while ((currentChar == '\\') && (peekChar() == 't')){
            		textBuffer.append("\\t");
            		valueBuffer.append('\t');
            		currentChar = nextChar();
            		currentChar = nextChar(); // consume '\t' character
            	}
            }
            //Check \n case
            if (currentChar == '\\'){
            	while((currentChar == '\\') && (peekChar() == 'n')){
            		textBuffer.append("\\n");
            		valueBuffer.append('\n');
            		currentChar = nextChar();
            		currentChar = nextChar();// consume '\n' character 
            	}
            }
            // Quote?  Each pair of adjacent quotes represents a single-quote.
            if (currentChar == '\"') {
                while ((currentChar == '\"') && (peekChar() == '\"')) {
                    textBuffer.append('"');
                    valueBuffer.append(currentChar); // append single-quote
                    currentChar = nextChar();        // consume pair of quotes
                    currentChar = nextChar();
                }
            }
            // Read normal character 
            if ((currentChar != '\"') && (currentChar != EOF)) {
                textBuffer.append(currentChar);
                valueBuffer.append(currentChar);
                currentChar = nextChar();  // consume character
            }
        } while ((currentChar != '\"') && (currentChar != EOF));

        if (currentChar == '\"') {
            nextChar();  // consume final quote
            textBuffer.append('\"');
            type = STRING;
            value = valueBuffer.toString();
        }
        else {
            type = ERROR;
            value = UNEXPECTED_EOF;
        }

        text = textBuffer.toString();
    }
}
