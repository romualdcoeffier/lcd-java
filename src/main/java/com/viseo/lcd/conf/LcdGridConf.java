package com.viseo.lcd.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.viseo.lcd.grid.LcdGrid;

/**
 * Configuration (mimic yml file).
 * Just keep configuration, no logic here (like checking, ...).
 * @see com.viseo.lcd.grid.LcdGrid
 */
public class LcdGridConf {

    public static LcdGridConf loadFrom(String resource) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        InputStream ymlStream = LcdGridConf.class.getResourceAsStream(resource);
        return mapper.readValue(ymlStream, LcdGridConf.class);
    }

    private final int height;
    private final int width;
    private final String allowedSymbols;
    private final List<LcdCharacterConf> characters;

    @JsonCreator
    public LcdGridConf(
            @JsonProperty("height") int height,
            @JsonProperty("width") int width,
            @JsonProperty("allowedSymbols") String allowedSymbols,
            @JsonProperty("characters") List<LcdCharacterConf> characters) {
        this.height = height;
        this.width = width;
        this.allowedSymbols = allowedSymbols;
        this.characters = characters;
    }

    /*package*/ int getHeight() {
        return height;
    }

    /*package*/ int getWidth() {
        return width;
    }

    /*package*/ String getAllowedSymbols() {
        return allowedSymbols;
    }

    /*package*/ List<LcdCharacterConf> getCharacters() {
        return characters;
    }

    /** @return grid from configuration. */
    public LcdGrid toGrid() {
        LcdGrid lcdGrid = new LcdGrid(height, width, allowedSymbols);
        characters.forEach(charConf -> {
            lcdGrid.addCharacter(charConf.getInput(), charConf.getOutput());
        });
        return lcdGrid;
    }

}
