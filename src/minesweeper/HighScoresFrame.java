package minesweeper;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class HighScoresFrame extends JFrame {

    private HighScores data;

    static JTable table;

    private void initComponents() {
        this.setLayout(new BorderLayout());

        table = new JTable(data);
        table.setFillsViewportHeight(true);

        table.setRowSorter(new TableRowSorter<>(data));

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));

        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.sort();

        table.setDefaultRenderer(String.class, new ScoreTableCellRenderer(table.getDefaultRenderer(String.class)));
        table.setDefaultRenderer(Enum.class, new ScoreTableCellRenderer(table.getDefaultRenderer(Enum.class)));
        table.setDefaultRenderer(Integer.class, new ScoreTableCellRenderer(table.getDefaultRenderer(Integer.class)));

        this.add(new JScrollPane(table),BorderLayout.CENTER);

        /*
        JPanel addPanel = new JPanel();
        addPanel.add(new JLabel("Name:"));
        JTextField newPlayerName = new JTextField(20);
        addPanel.add(newPlayerName);

        addPanel.add(new JLabel("Difficulty:"));
        JTextField newDiff = new JTextField(10);
        addPanel.add(newDiff);

        addPanel.add(new JLabel("Time:"));
        JTextField newTime = new JTextField(3);
        addPanel.add(newTime);

        JButton addButton = new JButton("Add Manually");
        addButton.addActionListener(ae -> data.addScore(newPlayerName.getText(), Difficulty.valueOf(newDiff.getText()), Integer.parseInt(newTime.getText())));
        addPanel.add(addButton);

        this.add(addPanel,BorderLayout.SOUTH);
         */
    }

    public HighScoresFrame() {
        super("High Scores");

        try {
            data = new HighScores();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scores.dat"));
            data.scores = (List<Score>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("scores.dat"));
                    oos.writeObject(data.scores);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setMinimumSize(new Dimension(350, 400));
        initComponents();
    }

    static class ScoreTableCellRenderer implements TableCellRenderer {
        private final TableCellRenderer renderer;

        public ScoreTableCellRenderer(TableCellRenderer defRenderer) {
            if (defRenderer==table.getDefaultRenderer(Enum.class)){
                this.renderer = table.getDefaultRenderer(String.class);
            }else
                this.renderer = defRenderer;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}