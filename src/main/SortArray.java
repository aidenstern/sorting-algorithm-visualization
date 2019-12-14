package main;

import screens.SortPanel;

import java.util.ArrayList;
import java.util.Collections;

public class SortArray {
    private final SortPanel sortPanel;
    private ArrayList<Integer> list;
    private int length;
    private int delay = 50;

    public SortArray(int length, SortPanel sortPanel) {
        this.length = length;
        this.sortPanel = sortPanel;
        list = new ArrayList<>();
        createUniqueList(list);
    }

    public void createUniqueList(ArrayList<Integer> list) {
        for (int i = 0; i < length; i++) {
            list.add(i, i * 2 + 10);
        }
        shuffle();
    }

    public int size() {
        return list.size();
    }

    public void shuffle() {
        Collections.shuffle(list);
    }

    public int get(int index) {
        return list.get(index);
    }

    public void swap(int firstIndex, int secondIndex) {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                int temp = list.get(firstIndex);
                list.set(firstIndex, list.get(secondIndex));
                list.set(secondIndex, temp);
                sortPanel.repaint();
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void set(int index, int value) {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                list.set(index, value);
                sortPanel.repaint();
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
