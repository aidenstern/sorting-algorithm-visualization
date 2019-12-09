package screens;

import algorithms.SortingAlgorithm;
import main.SortArray;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SortPanel extends JPanel {
    public static final int WIDTH = 420;
    public static final int HEIGHT = 160;
    public static final int BAR_WIDTH = 5;
    public static final int NUM_BARS = WIDTH / BAR_WIDTH - 4;
    private SortArray array;
    private SortingAlgorithm algorithm;

    public SortPanel(SortingAlgorithm algorithm) {
        this.algorithm = algorithm;
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        TitledBorder title = BorderFactory.createTitledBorder(blackLine, algorithm.getName());
        title.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(title);
        array = new SortArray(NUM_BARS, this);
    }

    public void run() {
        algorithm.runSort(array);
    }

    public SortArray getArray() {
        return array;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        super.paintComponent(graphics);
        int count = 7;
        for (int i = 0; i < NUM_BARS; i++) {
            graphics.setColor(Color.gray);
            graphics.fillRect(count, HEIGHT - array.getValue(i) + 37, BAR_WIDTH, array.getValue(i));
            graphics.setColor(Color.black);
            graphics.drawRect(count, HEIGHT - array.getValue(i) + 37, BAR_WIDTH, array.getValue(i));
            count += BAR_WIDTH;
        }
    }
}
