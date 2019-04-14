package hu.unideb.inf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Game {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        Ring ring = new Ring();
        logger.debug("Ring: {}", ring.getNumbers());
        while(true) {
            input = in.nextLine();

            if (input.equals("QUIT")) {
                logger.info("Closing application..");
                break;
            }

            else if (input.equals("LEFT")) {
                logger.info("Turning ring anti-clockwise.");
                ring.turnLeft();
            }

            else if (input.equals("RIGHT")) {
                logger.info("Turning ring clockwise.");
                ring.turnRight();
            }

            else if (input.equals("REVERSE")) {
                logger.info("Reversing numbers in 'squared' (first four) positions.");
                ring.reverse();
            }

            else {
                logger.info("Unknown command. Command list: LEFT, RIGHT, REVERSE and QUIT.");
            }

            logger.debug("Ring: {}", ring.getNumbers());

        }
    }
}
