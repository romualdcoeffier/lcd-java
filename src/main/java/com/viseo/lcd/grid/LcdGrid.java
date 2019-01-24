package com.viseo.lcd.grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public LcdGrid(int height, int width, String allowedSymbols) {
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

    /**
     * Add input->output conversion.
     * Grid have to check parameters according to size and allowedSymbols.
     */
    public LcdGrid addCharacter(char input, List<String> output) {
        // check
        if (output.size() != height) {
            throw new GridException("Invalid configuration for '" + input + "': output height should be " + height);
        }
        output.forEach(line -> {
            if (line.length() != width) {
                throw new GridException("Invalid configuration for '" + input + "': output width should be " + width + " (line " + line + ")");
            }
            if (line.chars().anyMatch(c -> !allowedSymbols.contains("" + (char) c))) {
                throw new GridException("Invalid configuration for '" + input + "': allowed symbols are " + allowedSymbols + " (line " + line + ")");
            }
        });

        // add
        LcdCharacter character = new LcdCharacter(input, output);
        this.characters.put(character.getInput(), character);

        return this;
    }

    /**
     * Convert input string to grid output.
     * Not configured symbols in input are ignored.
     */
    public String outputFor(String input) {
        // get LcdCharacter for each char from input
        List<LcdCharacter> inputChars = input.chars()
                .mapToObj(c -> this.characters.get((char) c))
                .filter(Objects::nonNull)// ignore not configured characters in input
                .collect(Collectors.toList());

        // build output
        return IntStream.range(0, height)
                // every line is equals to line of each character separated by a space
                .mapToObj(lineNumber -> inputChars.stream().map(c -> c.getLine(lineNumber)).collect(Collectors.joining(" ")))
                // finish all output lines by a line separator (including the last)
                .collect(Collectors.joining(System.lineSeparator(), "", System.lineSeparator()));
    }

}
