package main;

import screens.SortView;

import java.util.ArrayList;
import java.util.Collections;


public class SortArray {
    private final SortView arrayVisualiser;
    private ArrayList<Integer> list;
    private int length;

    public SortArray(int length, SortView arrayVisualiser) {
        this.length = length;
        list = new ArrayList<>();
        createUniqueList(list);
        this.arrayVisualiser = arrayVisualiser;
    }

    public void createUniqueList(ArrayList<Integer> list) {
        for (int i = 0; i < length; i++) {
            list.add(i, (i * 5) + 10);
        }
        Collections.shuffle(list);
    }

    public int size() {
        return list.size();
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
            Thread.sleep(15);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
