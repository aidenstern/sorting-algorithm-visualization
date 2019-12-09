package main;

import screens.SortPanel;

import java.util.ArrayList;
import java.util.Collections;

public class SortArray {
    private final SortPanel arrayVisualiser;
    private ArrayList<Integer> list;
    private int length;

    public SortArray(int length, SortPanel arrayVisualiser) {
        this.length = length;
        list = new ArrayList<>();
        createUniqueList(list);
        this.arrayVisualiser = arrayVisualiser;
    }

    public void createUniqueList(ArrayList<Integer> list) {
        for (int i = 0; i < length; i++) {
            list.add(i, (i * 5) + 10);
        }
        shuffle();
    }

    public int size() {
        return list.size();
    }

    public void shuffle() {
        Collections.shuffle(list);
    }

    public int getValue(int index) {
        return list.get(index);
    }

    public void swap(int firstIndex, int secondIndex) {
        int temp = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, temp);
        arrayVisualiser.repaint();
        try {
            Thread.sleep(30);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
