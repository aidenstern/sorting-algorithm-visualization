package algorithms;

import main.SortArray;

public class HeapSort extends SortingAlgorithm {
    @Override
    public String getName() {
        return "Heap Sort";
    }

    @Override
    public void runSort(SortArray list) {
        int length = list.size();

        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(list, length, i);
        }

        for (int i = length - 1; i >= 0; i--) {
            list.swap(0, i);
            heapify(list, i, 0);
        }
    }

    private void heapify(SortArray list, int length, int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < length && list.get(left) > list.get(largest)) {
            largest = left;
        }

        if (right < length && list.get(right) > list.get(largest)) {
            largest = right;
        }

        if (largest != index) {
            list.swap(index, largest);
            heapify(list, length, largest);
        }
    }
}
