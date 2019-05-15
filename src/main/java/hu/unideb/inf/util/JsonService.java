package hu.unideb.inf.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import hu.unideb.inf.model.Result;
import hu.unideb.inf.model.Scoreboard;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Slf4j
public class JsonService {
    private static final Type RESULT_TYPE = new TypeToken<List<Result>>() {}.getType();

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
