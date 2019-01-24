package com.viseo.lcd.conf;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Configuration of LcdCharacter. */
public class LcdCharacterConf {

    private final char input;
    private final List<String> output;

    @JsonCreator
    public LcdCharacterConf(
            @JsonProperty("input") char input,
            @JsonProperty("output") List<String> output) {
        this.input = input;
        this.output = output;
    }

    public char getInput() {
        return input;
    }

    public List<String> getOutput() {
        return output;
    }

}
