package algorithms;

import main.SortArray;

public class BubbleSort extends SortingAlgorithm {

    @Override
    public String getName() {
        return "Bubble Sort";
    }

    @Override
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
