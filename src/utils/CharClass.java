package utils;

/*

Helpful utility class to classify the common kinds of characters
that can occur. I know Java's standard library has functions for these,
but they use UNICODE while this language uses ASCII. I don't want
the compiler to recognize any bizarre UNICODE characters as being
legal!

 */
public class CharClass {
    // The valid escape sequence characters in our language
    private static String escapeChars = "abfnrtv0\\\'\"?";
    private static String whitespace = " \n\t";

    /*
    Detects if ch is in the range of characters
    described by [low, hi] (ASCII)
     */
    public static Boolean inRange(Integer ch, Integer low, Integer hi) {
        return low <= ch && ch <= hi;
    }

    /*
    Checks if ch is a non-printable ASCII character
    */
    public static Boolean isNonPrintASCII(Integer ch) {
        return inRange(ch, 33, 126);
    }

    /*
    Checks if ch is an escape character
     */
    public static Boolean isEscape(Integer ch) {
        return escapeChars.indexOf(ch) != -1;
    }

    public static Boolean isWhitespace(Integer ch) {
        return whitespace.indexOf(ch) != -1;
    }

    /*
    Checks if ch is in [a-zA-z]
     */
    public static Boolean isLetter(Integer ch) {
        return inRange(ch, 65, 90)
            || inRange(ch, 97, 122);
    }

    /*
    Checks if ch is a digit ([0-9])
     */
    public static Boolean isDigit(Integer ch) {
        return inRange(ch, 48, 57);
    }

    /*
    Checks if ch is a valid octal digit ([0-7])
     */
    public static Boolean isOctalDigit(Integer ch) {
        return inRange(ch, 48, 55);
    }

    /*
    Checks if ch is a valid hexadecimal digit ([0-9A-Fa-f])
     */
    public static Boolean isHexDigit(Integer ch) {
        return isDigit(ch)
            || inRange(ch, 65, 70)    // A-F
            || inRange(ch, 97, 102);  // a-f
    }
}
