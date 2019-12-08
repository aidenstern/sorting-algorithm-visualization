package screens;

import algorithms.BubbleSort;
import main.SortArray;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SortView extends JPanel {
    public static final int WIDTH =  320;
    public static final int HEIGHT = 180;
    public static final int NUM_BARS = WIDTH / 10 - 2;
    private SortArray array;

    public SortView() {
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        array = new SortArray(NUM_BARS, this);
        this.repaint();
    }

    public void run() {
        new BubbleSort().runSort(array);
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
