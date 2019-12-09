package screens;


import algorithms.BubbleSort;
import algorithms.QuickSort;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class View {
    public static final int WIDTH = 1272;
    public static final int HEIGHT = 700;

    private JPanel panelMain;
    private ArrayList<SortPanel> sortList;
    private JFrame window;
    private ArrayList<Callable<Void>> taskList;
    private ExecutorService executor;

    public View() {
        // Instantiate threads to be used
        instantiate();

        // Button Panel
        JPanel panelButton = new JPanel();
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            Runnable run = this::start;
            executor.execute(run);
        });
        JButton shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener(e -> {
            Runnable run = this::shuffle;
            executor.execute(run);
        });
        panelButton.add(startButton);
        panelButton.add(shuffleButton);

        // Main Panel
        panelMain = new JPanel(new GridLayout(3, 3, 5, 5));
        populate();

        // Main Frame
        window = new JFrame("Sorting Algorithm Visualization");
        window.setLayout(new BorderLayout());
        window.add(panelButton, BorderLayout.PAGE_START);
        window.add(panelMain);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        window.setVisible(true);
        window.setResizable(false);
    }

    public void populate() {
        sortList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SortPanel sortPanel = new SortPanel(new BubbleSort());
            sortPanel.setSize(SortPanel.WIDTH, SortPanel.HEIGHT);
            sortList.add(i, sortPanel);
            panelMain.add(sortPanel);
        }
        for (int i = 5; i < 9; i++) {
            SortPanel sortPanel = new SortPanel(new QuickSort());
            sortPanel.setSize(SortPanel.WIDTH, SortPanel.HEIGHT);
            sortList.add(i, sortPanel);
            panelMain.add(sortPanel);
        }
    }

    public void shuffle() {
        for (SortPanel panel : sortList) {
            panel.getArray().shuffle();
            panel.repaint();
        }
    }

    public void instantiate() {
        taskList = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            int I = i;
            Callable<Void> callable = () -> {
                sortList.get(I).run();
                return null;
            };
            taskList.add(callable);
        }
        executor = Executors.newFixedThreadPool(17);
    }

    public void start() {
        try {
            executor.invokeAll(taskList);
        }
        catch (InterruptedException ie) {
           ie.printStackTrace();
        }
    }
}
