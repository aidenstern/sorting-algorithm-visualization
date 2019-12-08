package algorithms;

import main.SortArray;
import screens.SortView;

import java.util.ArrayList;

public class BubbleSort extends SortingAlgorithm {

    private int stepDelay = 2;

    public String getName() {
        return "Bubble Sort";
    }

    public long getDelay() {
        return stepDelay;
    }

    public void setDelay(int delay) {
        stepDelay = delay;
    }

    public void runSort(SortArray array) {
        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.getValue(j) > array.getValue(j + 1)) {
                    array.swap(j, j + 1);
                }
            }
        }
    }
}
