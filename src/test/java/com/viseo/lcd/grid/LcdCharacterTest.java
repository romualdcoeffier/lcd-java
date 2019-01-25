package com.viseo.lcd.grid;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class LcdCharacterTest {

    @Test
    public void testCreationAndGetters() {
        LcdCharacter lcdCharacter = new LcdCharacter('0', Arrays.asList(" _ ", "| |", "|_|"));

        assertThat(lcdCharacter.getInput()).isEqualTo('0');
        assertThat(lcdCharacter.getLine(0)).isEqualTo(" _ ");
        assertThat(lcdCharacter.getLine(1)).isEqualTo("| |");
        assertThat(lcdCharacter.getLine(2)).isEqualTo("|_|");
    }

}
