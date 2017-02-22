package lexer.scanner;

import token.IdentToken;
import token.Token;
import token.TokenAttribute;
import utils.FileLocation;
import utils.InputFileReader;

import java.util.HashMap;
import java.util.function.Predicate;

import static token.TokenAttribute.*;
import static utils.CharClass.*;
import static utils.InputFileReader.NOTREAD;

/*

Lexer code that returns tokens representing identifiers.
Identifiers are described by the following reg-ex:
    Ident -> [a-zA-Z_][0-9a-zA-Z]*

 */
public class IdentTokenScanner extends TokenScanner {
    private static HashMap<String, TokenAttribute> keywords;

    /*
    Need to initialize this to contain the keywords for our
    language.
     */
    static {
        keywords = new HashMap<>();

        keywords.put("if", IF);
        keywords.put("else", ELSE);
        keywords.put("for", FOR);
        keywords.put("int", INT);
        keywords.put("char", CHAR);
        keywords.put("print", PRINT);
    }

    @Override
    public Token scanToken(InputFileReader input) {
        FileLocation fileLoc = new FileLocation(input.getCurLoc());
        String ident = getIdentStr(input);
        if (ident == null) {
            //TODO: Report error here!
            return null;
        }

        TokenAttribute tokAttr = keywords.get(ident);
        return tokAttr != null ? new Token(tokAttr, fileLoc) : new IdentToken(fileLoc, ident);
    }

    private String getIdentStr(InputFileReader input) {
        int ch;
        if ((ch = input.readIf(isIdentStartP)) < 0) {
            return null;
        }

        StringBuilder identBuf = new StringBuilder("");
        do {
            identBuf.append(ch);
        } while ((ch = input.readIf(isIdentMiddleP)) > 0);

        // There was an illegal character in the identifier,
        // and it is not whitespace.
        if (ch == NOTREAD && !isWhitespace(ch)) {
            //TODO: Report error: illegal character in the identifier name!
            return null;
        }

        return identBuf.toString();
    }

    // Some predicates that will be useful across instances of this class.
    private static Predicate<Integer> isIdentStartP = (c) -> isLetter(c) || c == '_';
    private static Predicate<Integer> isIdentMiddleP = (c) -> isIdentStartP.test(c) || isDigit(c);
}
