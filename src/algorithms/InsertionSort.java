package algorithms;

import main.SortArray;

public class InsertionSort extends SortingAlgorithm {
    @Override
    public String getName() {
        return "Insertion Sort";
    }

    @Override
    public void runSort(SortArray list) {
        int length = list.size();
        for (int i = 1; i < length; i++) {
            int key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j) > key) {
                list.swap(j + 1, j);
                j--;
            }
            list.set(j + 1, key);
        }
    }

}
