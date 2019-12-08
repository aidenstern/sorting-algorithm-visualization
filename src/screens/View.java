package screens;


import algorithms.BubbleSort;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class View {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 765;

    private ArrayList<SortView> sortList;
    private JFrame window;
    private SortView sortView;

    public View() {
        // Main Frame
        window = new JFrame("Sorting Algorithm Visualization");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        window.setLayout(new GridLayout(4, 4, 5, 5));
        window.setVisible(true);
        window.setResizable(false);
        window.setBackground(Color.black);
        sortList = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            sortView = new SortView(new BubbleSort());
            sortList.add(i, sortView);
            window.add(sortView);
        }

        /**
        ArrayList<Callable<Void>> taskList = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            int I = i;
            Callable<Void> callable = () -> {
                sortList.get(I).run();
                return null;
            };
            taskList.add(callable);
        }

        ExecutorService executor = Executors.newFixedThreadPool(16);
        try {
            executor.invokeAll(taskList);
        }
        catch (InterruptedException ie) {

         }
        */
        sortList.get(0).run();
    }
}
