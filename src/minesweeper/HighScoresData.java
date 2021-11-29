package minesweeper;

import javax.swing.table.AbstractTableModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A dicsőséglista adatait tároló és kezelő osztály.
 * Csak egy példánya lehet, azaz singleton.
 */
public class HighScoresData extends AbstractTableModel {
    private static HighScoresData instance;

    private List<Score> scores = new ArrayList<>();

    /**
     * Konstruktor.
     * Példányosítja az osztályt és beolvassa az adatokat a scores file-ból.
     */
    private HighScoresData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scores.dat"));
            scores = (List<Score>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Visszadja az osztály egyetlen létező példányát.
     * Ha az osztálynak még nem létezik példánya, létrehoz egyet.
     *
     * @return az osztály példánya
     */
    public static HighScoresData getInstance() {
        if (instance == null) {
            instance = new HighScoresData();
        }
        return instance;
    }

    /**
     * Hozzáad egy új rekordot a dicsőséglistához és a scores file-ba írja
     *
     * @param playerName a játékos neve
     * @param diff       a játék aktuális nehézségi szintje
     * @param time       a játékos időeredménye
     */
    public void addScore(String playerName, Difficulty diff, int time) {
        scores.add(new Score(playerName, diff, time));
        fireTableDataChanged();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("scores.dat"));
            oos.writeObject(getScores());
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return scores.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Score score = scores.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> score.getPlayerName();
            case 1 -> score.getDiff();
            default -> score.getTime();
        };
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Name";
            case 1 -> "Difficulty";
            default -> "Time";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> String.class;
            case 1 -> Difficulty.class;
            default -> Integer.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex > 1);
    }

    /**
     * Visszatér a dicsőséglista elemeit tároló listával.
     *
     * @return a dicsőséglista elemeit tároló lista
     */
    public List<Score> getScores() {
        return scores;
    }
}