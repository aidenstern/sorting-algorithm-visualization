package screens;


import algorithms.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class View {
    public static final int WIDTH = 1272;
    public static final int HEIGHT = 700;

    private JPanel panelAlgorithms;
    private JPanel panelMenu;
    private JPanel panelMain;
    private JPanel panelButton;
    private ArrayList<SortPanel> sortList;
    private ArrayList<JComboBox<String>> comboList;
    private Queue<String> choices;
    private String[] algorithmList;
    private CardLayout cards;
    private ArrayList<Thread> taskList;
    private ExecutorService executor;
    private int count;

    public View() {
        // Instantiate threads used for buttons
        executor = Executors.newFixedThreadPool(5);

        // Button Panel
        panelButton = new JPanel();
        populateButton();
        panelButton.setBackground(Color.black);

        // Menu Panel
        panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setBackground(Color.black);
        choices = new LinkedList<>();
        comboList = new ArrayList<>();
        algorithmList = new String[]{"Bubble Sort", "Cocktail Sort", "Heap Sort", "Insertion Sort",
                "Merge Sort", "Quick Sort", "Radix Sort", "Selection Sort", "None"};
        populateMenu();

        // Algorithms Panel
        JPanel algorithmMain = new JPanel(new BorderLayout());
        algorithmMain.add(panelButton, BorderLayout.PAGE_START);
        algorithmMain.setBackground(Color.black);
        panelAlgorithms = new JPanel(new GridLayout(3, 3, 4, 4));
        panelAlgorithms.setBackground(Color.black);
        algorithmMain.add(panelAlgorithms);

        // Main Panel (cardLayout)
        panelMain = new JPanel();
        cards = new CardLayout();
        panelMain.setLayout(cards);
        panelMain.add(panelMenu);
        panelMain.add(algorithmMain);

        // Main Frame
        JFrame window = new JFrame("Sorting Algorithm Visualization");
        window.add(panelMain);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        window.setVisible(true);
        window.setResizable(false);
    }

    private void populateButton() {
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
        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> {
            cards.next(panelMain);
            stop();
        });
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> {
            Runnable run = this::stop;
            executor.execute(run);
        });
        JSpinner delaySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 500, 1));
        delaySpinner.setValue(50);
        delaySpinner.addChangeListener(e -> {
            for (SortPanel sort : sortList) {
                sort.getArray().setDelay((int) delaySpinner.getValue());
            }
        });
        JLabel delayLabel = new JLabel("Millisecond delay:");
        delayLabel.setForeground(Color.white);
        panelButton.add(startButton);
        panelButton.add(stopButton);
        panelButton.add(shuffleButton);
        panelButton.add(menuButton);
        panelButton.add(delayLabel);
        panelButton.add(delaySpinner);
    }

    private void populateMenu() {
        JPanel panelFull = new JPanel(new GridLayout(3, 1, 50, 50));
        panelFull.setBackground(Color.darkGray);
        for (int i = 0; i < 3; i++) {
            JPanel panelSub = new JPanel(new GridLayout(1, 3, 50, 50));
            panelSub.setBackground(Color.darkGray);
            for (int j = 0; j < 3; j++) {
                JComboBox<String> algorithmChoice = new JComboBox<>(algorithmList);
                comboList.add(algorithmChoice);
                panelSub.add(algorithmChoice);
            }
            panelFull.add(panelSub);
        }
        JButton enterButton = new JButton("Enter");
        enterButton.setPreferredSize(new Dimension(80, 30));
        enterButton.addActionListener(e -> {
            for (int i = 0; i < algorithmList.length; i++) {
                int index = comboList.get(i).getSelectedIndex();
                choices.offer(algorithmList[index]);
            }
            clearScreen();
            populateMain();
            cards.next(panelMain);
        });
        JPanel buttonFormat = new JPanel(new GridBagLayout());
        buttonFormat.setMaximumSize(new Dimension(150, 75));
        buttonFormat.add(enterButton);
        buttonFormat.setBackground(Color.darkGray);
        buttonFormat.setBorder(BorderFactory.createLineBorder(Color.white));
        JPanel panelFormat = new JPanel(new GridBagLayout());
        panelFormat.setMaximumSize(new Dimension(475, 225));
        panelFormat.add(panelFull);
        panelFormat.setBackground(Color.darkGray);
        panelFormat.setBorder(BorderFactory.createLineBorder(Color.white));
        JPanel panelImage = new JPanel(new GridBagLayout());
        panelImage.setMaximumSize(new Dimension(600, 200));
        panelImage.setBackground(Color.black);
        panelImage.add(new JLabel(new ImageIcon("src\\resources\\sortlogo.png")));
        panelMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMenu.add(panelImage);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMenu.add(panelFormat);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 50)));
        panelMenu.add(buttonFormat);
    }

    private void populateMain() {
        sortList = new ArrayList<>();
        while (sortList.size() <= 9) sortList.add(new SortPanel(new BubbleSort()));
        while (choices.peek() != null) {
            if (choices.peek().equals(algorithmList[0])) {
                addAlgorithm(count, new SortPanel(new BubbleSort()));
                choices.poll();
                count++;
            } else if (choices.peek().equals(algorithmList[1])) {
                addAlgorithm(count, new SortPanel(new CocktailSort()));
                choices.poll();
                count++;
            } else if (choices.peek().equals(algorithmList[2])) {
                addAlgorithm(count, new SortPanel(new HeapSort()));
                choices.poll();
                count++;
            } else if (choices.peek().equals(algorithmList[3])) {
                addAlgorithm(count, new SortPanel(new InsertionSort()));
                choices.poll();
                count++;
            } else if (choices.peek().equals(algorithmList[4])) {
                addAlgorithm(count, new SortPanel(new MergeSort()));
                choices.poll();
                count++;
            } else if (choices.peek().equals(algorithmList[5])) {
                addAlgorithm(count, new SortPanel(new QuickSort()));
                choices.poll();
                count++;
            } else if (choices.peek().equals(algorithmList[6])) {
                addAlgorithm(count, new SortPanel(new RadixSort()));
                choices.poll();
                count++;
            } else if (choices.peek().equals(algorithmList[7])) {
                addAlgorithm(count, new SortPanel(new SelectionSort()));
                choices.poll();
                count++;
            } else if (choices.peek().equals(algorithmList[8])) {
                createEmpty();
                choices.poll();
                count++;
            }
        }
        count = 0;
    }

    private void addAlgorithm(int index, SortPanel algorithm) {
        sortList.set(index, algorithm);
        panelAlgorithms.add(algorithm);
    }

    private void clearScreen() {
        panelAlgorithms.removeAll();
    }

    private void shuffle() {
        for (SortPanel sort : sortList) {
            sort.getArray().shuffle();
            sort.repaint();
        }
    }

    private void createEmpty() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panelAlgorithms.add(panel);
    }

    private void start() {
        taskList = new ArrayList<>();
        for (SortPanel sort : sortList) {
            Thread t = new Thread(sort);
            taskList.add(t);
            t.start();
        }
    }

    private void stop() {
        for (Thread t : taskList) {
            t.interrupt();
        }
    }
}
