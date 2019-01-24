package com.viseo.lcd.grid;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class LcdGridTest {

    // test grid building

    @Test
    public void testEmptyGrid() throws GridException {
        LcdGrid lcdGrid = new LcdGrid(2, 2, " -|");
        assertThat(lcdGrid.outputFor("0123456789")).isEmpty();
    }

    @Test
    public void testExceptionWhenBuildGridWithInvalidHeight() {
        assertThatExceptionOfType(GridException.class)
                .isThrownBy(() -> new LcdGrid(0, 3, " -|"))
                .withMessageContaining("height");
    }

    @Test
    public void testExceptionWhenBuildGridWithInvalidWidth() {
        assertThatExceptionOfType(GridException.class)
                .isThrownBy(() -> new LcdGrid(3, 0, " -|"))
                .withMessageContaining("width");
    }

    @Test
    public void testExceptionWhenAddCharacterThatDoNotMatchHeightRequirement() throws GridException {
        LcdGrid grid = new LcdGrid(2, 3, " -|"); // height x width

        // output with only 1 line
        assertThatExceptionOfType(GridException.class)
                .isThrownBy(() -> grid.addCharacter('1', Arrays.asList("||")))
                .withMessageContaining("height")
                .withMessageContaining("2");

        // output with 3 lines
        assertThatExceptionOfType(GridException.class)
                .isThrownBy(() -> grid.addCharacter('1', Arrays.asList("||", "||", "||")))
                .withMessageContaining("height")
                .withMessageContaining("2");
    }

    @Test
    public void testExceptionWhenAddCharacterThatDoNotMatchWidthRequirement() throws GridException {
        LcdGrid grid = new LcdGrid(2, 3, " -|"); // height x width

        // output with 2 line but second is too shot
        assertThatExceptionOfType(GridException.class)
                .isThrownBy(() -> grid.addCharacter('1', Arrays.asList("||", "|")))
                .withMessageContaining("width")
                .withMessageContaining("3");

        // output with 2 line but second is too long
        assertThatExceptionOfType(GridException.class)
                .isThrownBy(() -> grid.addCharacter('1', Arrays.asList("||", "|||")))
                .withMessageContaining("width")
                .withMessageContaining("3");
    }

    @Test
    public void testExceptionWhenAddCharacterThatDoNotContainAllowedSymbols() throws GridException {
        LcdGrid grid = new LcdGrid(2, 2, "abc"); // height x width

        // 'd' not allowed in output
        assertThatExceptionOfType(GridException.class)
                .isThrownBy(() -> grid.addCharacter('1', Arrays.asList("ab", "cd")))
                .withMessageContaining("d")
                .withMessageContaining("abc");
    }

    // tests input -> output

    @Test
    public void testInputToOutput_3x3() throws GridException {
        LcdGrid grid = new LcdGrid(3, 3, ".-|")
                .addCharacter('0', Arrays.asList("._.", "|.|", "|_|"))
                .addCharacter('1', Arrays.asList("...", "..|", "..|"))
                .addCharacter('2', Arrays.asList("._.", "._|", "|_."))
                .addCharacter('3', Arrays.asList("._.", "._|", "._|"))
                .addCharacter('4', Arrays.asList("...", "|_|", "..|"))
                .addCharacter('5', Arrays.asList("._.", "|_.", "._|"))
                .addCharacter('6', Arrays.asList("._.", "|_.", "|_|"))
                .addCharacter('7', Arrays.asList("._.", "..|", "..|"))
                .addCharacter('8', Arrays.asList("._.", "|_|", "|_|"))
                .addCharacter('9', Arrays.asList("._.", "|_|", "..|"));

        assertThat(grid.outputFor("")).isEqualTo("");
        assertThat(grid.outputFor("3")).isEqualTo(
                "._." + System.lineSeparator() +
                "._|" + System.lineSeparator() +
                "._|" + System.lineSeparator());
        assertThat(grid.outputFor("27")).isEqualTo(
                "._. ._." + System.lineSeparator() +
                "._| ..|" + System.lineSeparator() +
                "|_. ..|" + System.lineSeparator());
        assertThat(grid.outputFor("910")).isEqualTo(
                "._. ... ._." + System.lineSeparator() +
                "|_| ..| |.|" + System.lineSeparator() +
                "..| ..| |_|" + System.lineSeparator());
        // unknown inputs are ignored
        assertThat(grid.outputFor("a9b1c0d")).isEqualTo(
                "._. ... ._." + System.lineSeparator() +
                "|_| ..| |.|" + System.lineSeparator() +
                "..| ..| |_|" + System.lineSeparator());
    }

}
