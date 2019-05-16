package hu.unideb.inf.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    @Test
    void getControllerTest() {
        GameController controller1 = GameController.getController();
        GameController controller2 = GameController.getController();

        assertEquals(controller1, controller2);
    }

}