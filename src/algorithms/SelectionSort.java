package algorithms;

import main.SortArray;

public class SelectionSort extends SortingAlgorithm {
    @Override
    public String getName() {
        return "Selection Sort";
    }

    @Override
    public void runSort(SortArray list) {
        int length = list.size();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < length - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < length; j++)
                if (list.get(j) < list.get(min_idx))
                    min_idx = j;
            list.swap(min_idx, i);
        }
    }
}
