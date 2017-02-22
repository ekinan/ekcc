package lexer.scanner;

import token.IntToken;
import token.Token;
import utils.FileLocation;
import utils.InputFileReader;

import java.util.function.Function;
import java.util.function.Predicate;

import static utils.CharClass.*;
import static utils.InputFileReader.NOTREAD;

/*

Lexer code that returns integer literals.
Integers are described by the following regex:
    IntLit -> T | H | O
    T -> [1-9][0-9]*
    H -> 0[xX][0-9a-fA-F]+
    O -> 0[0-7]*

 */
public class IntTokenScanner extends TokenScanner {
    @Override
    public Token scanToken(InputFileReader input) {
        FileLocation fileLoc = new FileLocation(input.getCurLoc());
        Integer val = parseIntLit(input);

        return val != null ? new IntToken(fileLoc, val) : null;
    }


    /*
    Helper method that parses out the actual integer literal, but delegates
    the work to another method of the same name.
     */
    private Integer parseIntLit(InputFileReader input) {
        if (input.skipIf(isZeroP)) { // Octal or hex
            if (input.skipIf(isXP)) { // Hex
                return parseIntLit(input, 16);
            }

            return parseIntLit(input, 8); // Octal
        }

        return parseIntLit(input, 10); // Base ten
    }

    /*
    Does the actual work of parsing out an integer literal in the
    given base.
     */
    private Integer parseIntLit(InputFileReader input, Integer base) {
        Predicate<Integer> isValidFirstDigitP = validFirstDigitPredicateFM.apply(base);
        Predicate<Integer> isValidDigitP = validDigitPredicateFM.apply(base);
        Function<Integer, Integer> charToInt = charToIntConvFM.apply(base);

        // Check if the first digit is there.
        int ch = '0';
        if (isValidFirstDigitP != null && (ch = input.readIf(isValidFirstDigitP)) < 0) {
            return null;
        }

        // Parse the remaining digits, note octal we're OK b/c ch will be the 0 character
        // here.
        Integer val = base*charToInt.apply(ch);
        while ((ch = input.readIf(isValidDigitP)) > 0) {
            val += val*base + charToInt.apply(ch);
        }

        // Illegal character was found if it was not whitespace that the
        // reader caught.
        if (ch == NOTREAD && !isWhitespace(ch)) {
            //TODO: Report error! Illegal character in a numeric sequence.
            return null;
        }

        return val;
    }

    /*
    Factory method to return the relevant factory method that, given a base,
    figures out the correct result of the type T to pass out.
     */
    private static <T> Function<Integer, T> createBaseFM(T numRes, T hexRes, T octalRes) {
        return (base) -> {
            switch (base) {
                case 10:
                    return numRes;
                case 16:
                    return hexRes;
                case 8:
                    return octalRes;
            }

            return null;
        };
    }


    // Common functions that will be used. This avoids recreating
    // them every single time they're needed when the scanToken method
    // is invoked.
    private static Predicate<Integer> isZeroP = (c) -> c == '0';
    private static Predicate<Integer> isPosDigitP = (c) -> c != '0' && isDigit(c);
    private static Predicate<Integer> isXP = (c) -> c == 'x' || c == 'X';
    private static Predicate<Integer> isDigitP = (c) -> isDigit(c);
    private static Predicate<Integer> isHexDigitP = (c) -> isHexDigit(c);
    private static Predicate<Integer> isOctalDigitP = (c) -> isOctalDigit(c);


    private static Function<Integer, Integer> digCharToInt = (c) -> c - '0';
    private static Function<Integer, Integer> hexCharToInt = (c) -> {
        if (inRange(c, 65, 70)) { // For A - F digits
            return 10 + (c - 65);
        }

        if (inRange(c, 97, 102)) { // For a - f digits
            return 10 + (c - 97);
        }

        return c - '0'; // For all other digits
    };

    private static Function<Integer, Predicate<Integer>> validDigitPredicateFM
            = createBaseFM(isDigitP, isHexDigitP, isOctalDigitP);
    private static Function<Integer, Predicate<Integer>> validFirstDigitPredicateFM
            = createBaseFM(isPosDigitP, isHexDigitP, null);
    private static Function<Integer, Function<Integer, Integer>>  charToIntConvFM
            = createBaseFM(digCharToInt, hexCharToInt, digCharToInt);
}
