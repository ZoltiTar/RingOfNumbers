package hu.unideb.inf;

import com.github.lalyos.jfiglet.FigletFont;
import hu.unideb.inf.controller.LaunchController;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Game {

    public static void main(String[] args) {
        log.info("Starting Ring of Numbers...");

        String welcome;
        try {
            welcome = "Welcome to\n".concat(FigletFont.convertOneLine("Ring of Numbers"));
        } catch (IOException e) {
            welcome = "Welcome to Ring of Numbers.";
        }
        System.out.println(welcome);
        LaunchController launchController = LaunchController.getController();
        launchController.start();
    }
}
