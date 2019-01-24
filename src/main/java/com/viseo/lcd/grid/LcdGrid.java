package com.viseo.lcd.grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Grid logic (= transform input list to output).
 */
public class LcdGrid {

    // grid size
    private final int height;
    private final int width;

    /** Allowed symbols in output. */
    private final String allowedSymbols;

    /** All input/output converters by input. */
    private final Map<Character, LcdCharacter> characters = new HashMap();

    public LcdGrid(int height, int width, String allowedSymbols) throws GridException {
        if (height < 2) {
            throw new GridException("Invalid configuration: min height is 2");
        }
        this.height = height;

        if (width < 2) {
            throw new GridException("Invalid configuration: min width is 2");
        }
        this.width = width;

        this.allowedSymbols = allowedSymbols;
    }

    public LcdGrid addCharacter(char input, List<String> output) throws GridException  {
        // TODO
        return this;
    }

    public String outputFor(String input) {
        // TODO
        return "";
    }

}
