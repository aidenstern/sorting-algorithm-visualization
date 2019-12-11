package algorithms;

import main.SortArray;

public class BubbleSort extends SortingAlgorithm {
    private volatile boolean flag = false;

    public String getName() {
        return "Bubble Sort";
    }

    public void runSort(SortArray array) {
        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    array.swap(j, j + 1);
                }
            }
        }
    }
}
