package hu.unideb.inf;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Game {

    private static StopWatch timer = new StopWatch();

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    public static void main(String[] args) {

        logger.info("Starting Ring of Numbers...");
        Ring ring = new Ring();
        Scanner in = new Scanner(System.in);
        String input;

        System.out.print("Enter your name: ");
        input = in.nextLine();
        Player player = new Player(input, 0, 0);
        logger.info("Creating new player '{}'", input);

        System.out.println("Press Enter to start the game.");
        in.nextLine();

        System.out.println("Starting now, good luck!");
        timer.start();
        timer.split();
        logger.debug("Ring: {}\n Time: {}, Steps: {}", ring.getNumbers(), timer.toSplitString(), player.getSteps());

        gameLoop:
        while (true) {
            if (ring.complete()) {
                logger.info("Game completed by {} in {} seconds and {} steps.",
                        player.getName(), player.getTime(), player.getSteps());
                break;
            }

            input = in.nextLine();

            switch (input) {
                case "QUIT":
                    logger.info("Closing application...");
                    break gameLoop;
                case "LEFT":
                    logger.info("Turning ring anti-clockwise.");
                    ring.turnLeft();
                    player.step();
                    break;
                case "RIGHT":
                    logger.info("Turning ring clockwise.");
                    ring.turnRight();
                    player.step();
                    break;
                case "REVERSE":
                    logger.info("Reversing numbers in 'squared' (first four) positions.");
                    ring.reverse();
                    player.step();
                    break;
                default:
                    logger.info("Unknown command. Command list: LEFT, RIGHT, REVERSE and QUIT.");
                    break;
            }

            timer.split();
            logger.debug("Ring: {}\n Time: {}, Steps: {}", ring.getNumbers(), timer.toSplitString(), player.getSteps());

        }
    }

}
