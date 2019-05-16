package hu.unideb.inf.model;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import lombok.*;

import java.util.ArrayList;

/**
 * Class for holding the top 10 (local) results of the puzzle.
 */
@Data
@RequiredArgsConstructor
public class Scoreboard {

    /**
     * The list holding the 10 results, ordered.
     */
    @NonNull
    private ArrayList<Result> results;

    /**
     * Creates new Scoreboard object, with an empty (but instantiated) list.
     */
    public Scoreboard() {
        this.results = new ArrayList<>();
    }

    /**
     * Checks whether the scoreboard is empty.
     *
     * @return {@code true} if the scoreboard is empty, false otherwise.
     */
    public boolean isEmpty() {
        return results.isEmpty();
    }

    /**
     * Inserts a new result into the scoreboard if it is eligible to be inserted, then trims the list of results
     * if it's size would go over 10 after insertion.
     *
     * @param result the new result to be inserted to the current scoreboard
     */
    public void add(Result result) {
        if (isEmpty()) {
            results.add(result);
        } else if (result.isSolved()) {
            addSolved(result);
        } else {
            addAttempt(result);
        }
        results = results.size() > 10 ? (ArrayList<Result>) results.subList(0, 10) : results;
    }

    /**
     * Inserts a solved result of the puzzle into the scoreboard, comparing steps made, name of the player and
     * if the puzzle was solved. If the list of results contains attempts of the puzzle (not solved results),
     * inserts the new result to the place of the first attempt. Else checks steps made, if equal,
     * compares names of the players.
     *
     * @param result the new result to be inserted into the list
     */
    private void addSolved(Result result) {
        for (int i = 0; i < results.size(); i++) {
            Result res = results.get(i);
            if (!res.isSolved()) {
                results.add(i, result);
                break;
            }
            if (result.getSteps() < res.getSteps()) {
                results.add(i, result);
                break;
            } else if (result.getSteps() == res.getSteps()) {
                results.add((result.getName().compareToIgnoreCase(res.getName()) > -1) ? i : i + 1, result);
                break;
            }
        }
    }

    /**
     * Inserts an attempt of the puzzle (unsolved result) into the scoreboard, comparing steps made,
     * name of the player. First it skips the solved results, then checks steps made, if equal,
     * compares names of the players.
     *
     * @param result the new attempt to be inserted into the list
     */
    private void addAttempt(Result result) {
        for (int i = 0; i < results.size(); i++) {
            Result res = results.get(i);
            if (res.isSolved()) continue;
            if (result.getSteps() > res.getSteps()) {
                results.add((result.getName().compareToIgnoreCase(res.getName()) > -1) ? i : ++i, result);
                break;
            } else if (result.getSteps() == res.getSteps()) {
                results.add((result.getName().compareToIgnoreCase(res.getName()) > -1) ? i : i + 1, result);
                break;
            }
        }
    }

    /**
     * Returns the scoreboard as a table.
     *
     * @return rendered value of an asciitable (provided by the de.vandermeer.asciitable package).
     */
    @Override
    public String toString() {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.getRenderer().setCWC(new CWC_LongestLine().add(0, 2).add(0, 30).add(0, 0).add(0, 0));
        table.getContext().setFrameLeftRightMargin(3);
        table.addRow(null, null, null, "Local Scoreboard");
        table.addRule();
        table.addRow("#", "Player", "Steps", "Result");
        table.addRule();
        if (results.isEmpty()) {
            table.addRow(null, null, null, "No records to show.")
                    .getCells()
                    .get(3).getContext()
                    .setTextAlignment(TextAlignment.CENTER);
            table.addRule();
        } else {
            for (int i = 0; i < results.size(); i++) {
                Result result = results.get(i);
                String name = result.getName();
                if (name.length() > 27) name = name.substring(0, 27).concat("...");
                table.addRow(i + 1, name, result.getSteps(), result.isSolved() ? "Solved" : "Attempt");
                table.addRule();
            }
        }

        table.setTextAlignment(TextAlignment.CENTER);
        return table.render();
    }
}
