package utils;

/*

Same as FileLocation, but mutable. This is good to use for a global
counter of the current file's line and column numbers.

 */
public class MutableFileLocation extends FileLocation {
    public MutableFileLocation(Integer line, Integer col) {
        super(line, col);
    }

    // Update the current location based on the character
    // that was passed in. If it's a new line character,
    // increment the current line number and reset the
    // column to 0.
    public void update(int ch) {
        if (ch == -1) {
            return;
        }

        if (ch == '\n') {
            line++;
            col = 0;
            return;
        }

        col++;
    }
}
