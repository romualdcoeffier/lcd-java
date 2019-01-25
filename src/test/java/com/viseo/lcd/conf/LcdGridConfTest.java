package com.viseo.lcd.conf;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.viseo.lcd.grid.LcdGrid;

public class LcdGridConfTest {

    @Test
    public void testLoadConfiguration() throws IOException {
        LcdGridConf conf = LcdGridConf.loadFrom("/lcd-test.yml");

        assertThat(conf.getHeight()).isEqualTo(2);
        assertThat(conf.getWidth()).isEqualTo(4);
        assertThat(conf.getAllowedSymbols()).isEqualTo("._|");
        assertThat(conf.getCharacters()).hasSize(2);
        assertThat(conf.getCharacters().get(0).getInput()).isEqualTo('0');
        assertThat(conf.getCharacters().get(0).getOutput()).hasSize(2);
        assertThat(conf.getCharacters().get(0).getOutput().get(0)).isEqualTo(".__.");
        assertThat(conf.getCharacters().get(0).getOutput().get(1)).isEqualTo("|__|");
        assertThat(conf.getCharacters().get(1).getInput()).isEqualTo('1');
        assertThat(conf.getCharacters().get(1).getOutput()).hasSize(2);
        assertThat(conf.getCharacters().get(1).getOutput().get(0)).isEqualTo("....");
        assertThat(conf.getCharacters().get(1).getOutput().get(1)).isEqualTo("...|");
    }

    @Test
    public void testConvertConfigurationToGrid() throws IOException {
        LcdGridConf conf = LcdGridConf.loadFrom("/lcd-test.yml");
        LcdGrid grid = conf.toGrid();

        assertThat(grid.outputFor("10")).isEqualTo(
                ".... .__." + System.lineSeparator() +
                "...| |__|" + System.lineSeparator());
    }

}
