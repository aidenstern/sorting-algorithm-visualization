package screens;

import algorithms.BubbleSort;
import algorithms.SortingAlgorithm;
import main.SortArray;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SortView extends JPanel {
    public static final int WIDTH =  320;
    public static final int HEIGHT = 190;
    public static final int NUM_BARS = WIDTH / 10 - 2;
    private SortArray array;
    private SortingAlgorithm algorithm;

    public SortView(SortingAlgorithm algorithm) {
        this.algorithm = algorithm;
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        TitledBorder title = BorderFactory.createTitledBorder(blackLine, algorithm.getName());
        title.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(title);
        array = new SortArray(NUM_BARS, this);
        this.repaint();
    }

    public void run() {
        algorithm.runSort(array);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        super.paintComponent(graphics);
        int count = 5;
        for (int i = 0; i < NUM_BARS; i++) {
            graphics.setColor(Color.gray);
            graphics.fillRect(count, HEIGHT - array.getValue(i) - 20, 10, array.getValue(i));
            graphics.setColor(Color.black);
            graphics.drawRect(count, HEIGHT - array.getValue(i) - 20, 10, array.getValue(i));
            count += 10;
        }
    }
}
