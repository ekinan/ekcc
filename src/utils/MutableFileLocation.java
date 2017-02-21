package utils;

/*

Same as FileLocation, but mutable. This is good to use for a global
counter of the current file's line and column numbers.

 */
public class MutableFileLocation extends FileLocation {
    public MutableFileLocation(Integer line, Integer col) {
        super(line, col);
    }

    // Increments the current line number.
    public void incrLine() {
        this.line++;
    }

    // Increments the current column number.
    public void incrCol() {
        this.col++;
    }
}
