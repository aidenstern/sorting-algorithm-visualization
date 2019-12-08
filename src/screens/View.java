package screens;


import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.ArrayList;

public class View {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

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
            sortView = new SortView();
            sortList.add(i, sortView);
            window.add(sortView);
        }

        sortList.get(0).run();

        sortView.repaint();
    }
}
