package utils;

import java.io.*;
import java.util.function.Predicate;

/*

This class is what will read the input file for processing
Its purpose is to also keep a global tracker of where we're
currently at in the file. Basically, all input-specific operations
are handled here, including modifying the global counter.

*/
public class InputFileReader {
    // The reader that stores the input
    private BufferedReader reader;

    // The location we're at in the current file.
    private MutableFileLocation curLoc;

    public InputFileReader(String filePath) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(filePath));
        this.curLoc = new MutableFileLocation(0, 0);
    }

    // Looks at the next char in the sequence and returns it
    // If no such char exists, then it returns -1 to indicate
    // the end of the stream.
    public int peek() {
        try {
            reader.mark(1);
            int ch = reader.read();
            reader.reset();

            return ch == -1 ? EOF : ch;
        } catch (IOException e) {
            return EXCEPTION;
        }
    }

    // Same as the BufferedReader's read, but we need to also
    // update our location.
    public int read() {
        int ch;
        try {
            ch = reader.read();
        } catch (IOException e) {
            return EXCEPTION;
        }

        curLoc.update((char)ch);
        return ch == -1 ? EOF : ch;
    }

    // Same as read, but only reads the character if it
    // satisfies the predicate p. Returns -1 if it doesn't.
    public int readIf(Predicate<Integer> p) {
        int ch = peek();
        if (ch == EOF || !p.test(ch)) {
            return NOTREAD;
        }

        return read();
    }

    /*
    Skips the next character. If the EOF character is the next
    character or an exception occurs, then this method returns false.
    */
    public Boolean skip() {
        int ch = read();
        return ch != EOF && ch != EXCEPTION;
    }

    /*
    Skips the next character if it meets some passed in predicate.
     */
    public Boolean skipIf(Predicate<Integer> p) {
        int ch = peek();
        if (ch == EOF || !p.test(ch)) {
            return false;
        }

        return read() != EXCEPTION;
    }

    /*
    Skips the input stream until the first character not satisfying
    p is encountered. Returns the occurrence of this character
    if found, or EOF if the end of the file is reached.
     */
    public int skipWhile(Predicate<Integer> p) {
        while (skipIf(p));

        return peek();
    }

    /*
    Closes the input stream when it is no longer in use.
     */
    public void close() throws IOException {
        reader.close();
    }

    /*
    Returns the current location
     */
    public FileLocation getCurLoc() {
        return curLoc;
    }

    // Error return codes for read
    public static final int EOF = -1;
    public static final int NOTREAD = -2;
    public static final int EXCEPTION = -3;
}
