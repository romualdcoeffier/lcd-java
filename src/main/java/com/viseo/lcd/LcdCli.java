package com.viseo.lcd;

import com.viseo.lcd.conf.LcdGridConf;
import com.viseo.lcd.grid.LcdGrid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LcdCli {

    private static final Logger LOGGER = LogManager.getLogger(LcdCli.class);
    private static final Logger OUT = LogManager.getLogger("cli");

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                usage();
            }
            String input = args[0];
            run(input);
        } catch (Exception ex) {
            error(ex);
        }
    }

    private static void run(String input) throws IOException {
        LcdGridConf gridConf = LcdGridConf.loadFrom("/lcd.yml");
        LcdGrid lcdGrid = gridConf.toGrid();
        String output = lcdGrid.outputFor(input);
        OUT.info(output);
    }

    private static void usage() {
        OUT.info("Usage: lcd <digits>");
        System.exit(1);
    }

    private static void error(Exception ex) {
        LOGGER.error("LCD error:", ex);
        OUT.error("Error: " + ex.getMessage());
        System.exit(1);
    }

}
