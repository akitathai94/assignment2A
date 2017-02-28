package wci.frontend.Java;

import java.util.Hashtable;
import java.util.HashSet;

import wci.frontend.TokenType;

/**
 * <h1>JavaTokenType</h1>
 *
 * <p>Java token types.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public enum JavaTokenType implements TokenType
{
    // Reserved words.
    AND, ARRAY, BEGIN, CASE, CONST, DIV, DO, DOWNTO, ELSE, END,
    FILE, FOR, FUNCTION, GOTO, IF, IN, LABEL, MOD, NIL, NOT,
    OF, OR, PACKED, PROCEDURE, PROGRAM, RECORD, REPEAT, SET,
    THEN, TO, TYPE, UNTIL, VAR, WHILE, WITH, ABSTRACT, DOUBLE,
    INT, LONG, BREAK, SWITCH, ENUM, NATIVE, SUPER, CHAR, EXTENDS,
    RETURN, THIS, CLASS, FLOAT, SHORT, THROW, PACKAGE, VOID, CONTINUE,
    PROTECTED, VOLATILE, STATIC, 

    // Special symbols.
    PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), COLON_EQUALS(":="),
    DOT("."), COMMA(","), SEMICOLON(";"), COLON(":"), QUOTE("'"),
    EQUALS("="), NOT_EQUALS("<>"), LESS_THAN("<"), LESS_EQUALS("<="),
    GREATER_EQUALS(">="), GREATER_THAN(">"), LEFT_PAREN("("), RIGHT_PAREN(")"),
    LEFT_BRACKET("["), RIGHT_BRACKET("]"), LEFT_BRACE("{"), RIGHT_BRACE("}"),
    UP_ARROW("^"), DOT_DOT(".."), TILDE("~"), EXCLAMATION_MARK("!"), AT("@"),
    PERCENT_SIGN("%"), AMPERSAND("&"), VERTICAL_BAR("|"), 
    QUESTION_MARK("?"), PLUS_PLUS("++"), MINUS_MINUS("--"), LEFT_SHIFT("<<"),
    RIGHT_SHIFT(">>"), SLASH_SLASH("//"), SLASH_STAR("/*"), STAR_SLASH("*/"),
    PLUS_EQUAL("+="), MINUS_EQUAL("-="), STAR_EQUAL("*="), SLASH_EQUAL("/="),
    OR_EQUAL("|="), AND_EQUAL("&="), PERCENT_EQUAL("%="), UP_ARROW_EQUAL("^="),
    NOT_EQUAL("!="), AND_AND("&&"), OR_OR("||"), LEFT_SHIFT_EQUAL("<<="), DOUBLE_QUOTE("\""),
    RIGHT_SHIFT_EQUAL(">>="),

    IDENTIFIER, INTEGER, REAL, STRING,
    ERROR, END_OF_FILE;

    private static final int FIRST_RESERVED_INDEX = AND.ordinal();
    private static final int LAST_RESERVED_INDEX  = STATIC.ordinal();

    private static final int FIRST_SPECIAL_INDEX = PLUS.ordinal();
    private static final int LAST_SPECIAL_INDEX  = RIGHT_SHIFT_EQUAL.ordinal();

    private String text;  // token text

    /**
     * Constructor.
     */
    JavaTokenType()
    {
        this.text = this.toString();
    }

    /**
     * Constructor.
     * @param text the token text.
     */
    JavaTokenType(String text)
    {
        this.text = text;
    }

    /**
     * Getter.
     * @return the token text.
     */
    public String getText()
    {
        return text;
    }

    // Set of Upper-cased Java reserved word text strings.
    public static HashSet<String> RESERVED_WORDS = new HashSet<String>();
    static {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_RESERVED_INDEX; i <= LAST_RESERVED_INDEX; ++i) {
            RESERVED_WORDS.add(values[i].getText().toLowerCase());
        }
    }

    // Hash table of Java special symbols.  Each special symbol's text
    // is the key to its Java token type.
    public static Hashtable<String, JavaTokenType> SPECIAL_SYMBOLS =
        new Hashtable<String, JavaTokenType>();
    static {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_SPECIAL_INDEX; i <= LAST_SPECIAL_INDEX; ++i) {
            SPECIAL_SYMBOLS.put(values[i].getText(), values[i]);
        }
    }
}
