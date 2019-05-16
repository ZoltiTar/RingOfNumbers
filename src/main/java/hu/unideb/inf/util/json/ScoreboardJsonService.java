package hu.unideb.inf.util.json;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import hu.unideb.inf.model.Scoreboard;
import lombok.extern.slf4j.Slf4j;

import java.io.*;


/**
 * Json service for loading and saving local scoreboard from/to working directory.
 */
@Slf4j
public class ScoreboardJsonService {

    /**
     * The Gson object to read/write the scoreboard to working directory.
     */
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Updates the local scoreboard, does nothing, if there is {@code IOException} during the
     * creation of the {@code FileWriter} for the json file.
     *
     * @param scoreboard the new scoreboard to overwrite the old one
     *
     * @see FileWriter
     * @see IOException
     */
    public void updateScoreboard(Scoreboard scoreboard) {
        try {
            log.info("Saving scoreboard");
            FileWriter writer = new FileWriter("scoreboard.json");
            gson.toJson(scoreboard, writer);
            writer.close();
        } catch (IOException e) {
            log.warn("Error during scoreboard update.", e);
        }
    }

    /**
     * Reads the local scoreboard. If there is {@code FileNotFoundException} during the
     * creation of the {@code JsonReader} returns empty scoreboard.
     *
     * @return {@code Scoreboard} object of local scoreboard, or empty instance.
     *
     * @see JsonReader#JsonReader(Reader)
     * @see FileNotFoundException
     */
    public Scoreboard readScoreboard() {
        log.info("Loading scoreboard.");
        Scoreboard scoreboard = new Scoreboard();
        try {
            JsonReader reader = new JsonReader(new FileReader("scoreboard.json"));
            JsonElement jsonScoreboard = new JsonParser().parse(reader);
            scoreboard = gson.fromJson(jsonScoreboard, Scoreboard.class);
        } catch (FileNotFoundException e) {
            updateScoreboard(scoreboard);
        }
        return scoreboard;
    }

}
