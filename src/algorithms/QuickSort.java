package algorithms;

import main.SortArray;


public class QuickSort extends SortingAlgorithm {

    @Override
    public String getName() {
        return "Quick Sort";
    }

    @Override
    public void runSort(SortArray list) {
        quickSort(list, 0, list.size() - 1);
    }

    private void quickSort(SortArray list, int idx1, int idx2) {
        if (idx1 >= idx2) {
            return;
        }
        int partitionIndex = partition(list, idx1, idx2);
        quickSort(list, idx1, partitionIndex);
        quickSort(list, partitionIndex + 1, idx2);
    }

    private int partition(SortArray list, int idx1, int idx2) {
        int pivot = list.get(idx1);
        int leftIndex = idx1 - 1;
        int rightIndex = idx2 + 1;
        while (leftIndex < rightIndex) {
            leftIndex++;
            while (pivot > list.get(leftIndex)) {
                leftIndex++;
            }
            rightIndex--;
            while (pivot < list.get(rightIndex)) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                list.swap(leftIndex, rightIndex);
            }
        }
        return rightIndex;
    }
}
