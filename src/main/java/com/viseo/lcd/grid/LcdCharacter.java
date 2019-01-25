package com.viseo.lcd.grid;

import java.util.List;

/**
 * How to transform an input (a character) to an output for the grid.
 */
/*package*/ class LcdCharacter {

    /** Character input. */
    private final char input;

    /** Output according to character input (a string per grid line). */
    private final List<String> output;

    /*package*/ LcdCharacter(char input, List<String> output) {
        this.input = input;
        this.output = output;
    }

    /*package*/ char getInput() {
        return input;
    }

    /*package*/ String getLine(int lineNumber) {
        return this.output.get(lineNumber);
    }

}
