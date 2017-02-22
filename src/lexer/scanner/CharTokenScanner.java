package lexer.scanner;

import token.CharToken;
import token.Token;
import utils.FileLocation;
import utils.InputFileReader;

import java.util.function.Predicate;

import static utils.CharClass.isEscape;
import static utils.CharClass.isNonPrintASCII;

/*

Lexer code that returns tokens representing characters
Characters are described by the following regex
    CharLit -> "'" Ch "'"
    Ch -> NonPrintASCII | "\" Escape
    Escape -> [abfnrtv0\'"?]
where ASCII is any ASCII character, and Escape corresponds
to escape sequences (e.g. \n, \t, \", etc.)

 */
public class CharTokenScanner extends TokenScanner {
    @Override
    public Token scanToken(InputFileReader input) {
        FileLocation fileLoc = new FileLocation(input.getCurLoc());
        if (!input.skipIf(isSingleQuoteP)) {
            return null;
        }

        int ch = getChar(input);
        if (ch < 0) {
            //TODO: Report error! No legal character found between the ''.
            return null;
        }

        //TODO: Report error if single quote is not encountered. Unterminated character!
        return input.skipIf(isSingleQuoteP) ? new CharToken(fileLoc, (char)ch) : null;
    }

    /*
    Checks that the underlying character surrounded by '' is a NonPrintASCII
    or an Escape character and returns it, otherwise it returns a -1.
     */
    private int getChar(InputFileReader input) {
        int ch;
        if (input.skipIf(isBackslashP)
        && ((ch = input.readIf(isEscapeP)) > 0)) {
            return ch;
        }

        return input.readIf(isNonPrintASCIIP);
    }

    // Some common predicates that will be needed for this class (to avoid
    // recreating them each time the methods in this class are invoked).
    private static final Predicate<Integer> isSingleQuoteP = (c) -> c == '\'';
    private static final Predicate<Integer> isBackslashP = (c) -> c == '\\';
    private static final Predicate<Integer> isEscapeP = (c) -> isEscape(c);
    private static final Predicate<Integer> isNonPrintASCIIP = (c) -> isNonPrintASCII(c);
}
