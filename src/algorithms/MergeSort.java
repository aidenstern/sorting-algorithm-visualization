package algorithms;

import main.SortArray;

import java.util.ArrayList;

public class MergeSort extends SortingAlgorithm {
    @Override
    public String getName() {
        return "Merge Sort";
    }

    @Override
    public void runSort(SortArray list) {
        mergeSort(list, 0, list.size() - 1);
    }

    private void mergeSort(SortArray list, int idx1, int idx2) {
        if (idx1 < idx2) {
            int middle = (idx1 + idx2) / 2;
            mergeSort(list, idx1, middle);
            mergeSort(list, middle + 1, idx2);
            merge(list, idx1, middle, idx2);
        }
    }

    private void merge(SortArray list, int idx1, int middle, int idx2) {
        int firstLength = middle - idx1 + 1;
        int secondLength = idx2 - middle;

        ArrayList<Integer> firstList = new ArrayList<>();
        for (int i = 0; i < firstLength; i++) {
            firstList.add(i, list.get(idx1 + i));
        }

        ArrayList<Integer> secondList = new ArrayList<>();
        for (int i = 0; i < secondLength; i++) {
            secondList.add(i, list.get(middle + 1 + i));
        }

        int i = 0;
        int j = 0;
        int k = idx1;
        while (i < firstLength && j < secondLength) {
            if (firstList.get(i) <= secondList.get(j)) {
                list.set(k, firstList.get(i));
                i++;
            } else {
                list.set(k, secondList.get(j));
                j++;
            }
            k++;
        }

        while (i < firstLength) {
            list.set(k, firstList.get(i));
            i++;
            k++;
        }

        while (j < secondLength) {
            list.set(k, secondList.get(j));
            j++;
            k++;
        }
    }

}
