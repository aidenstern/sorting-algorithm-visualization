package algorithms;

import main.SortArray;

import java.util.Arrays;

public class RadixSort extends SortingAlgorithm {
    @Override
    public String getName() {
        return "Radix Sort";
    }

    @Override
    public void runSort(SortArray list) {
        radixSort(list, list.size());
    }

    private void radixSort(SortArray list, int size) {
        int max = getMax(list, size);

        for (int i = 1; max / i > 0; i *= 10) {
            countSort(list, size, i);
        }
    }

    private void countSort(SortArray list, int size, int exp) {
        int[] output = new int[size]; // output array
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < size; i++) {
            count[(list.get(i) / exp) % 10]++;
        }

        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (i = size - 1; i >= 0; i--) {
            output[count[(list.get(i) / exp) % 10] - 1] = list.get(i);
            count[(list.get(i) / exp) % 10]--;
        }

        for (i = 0; i < size; i++) {
            list.set(i, output[i]);
        }
    }

    private int getMax(SortArray list, int size) {
        int max = list.get(0);
        for (int i = 1; i < size; i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }
        return max;
    }

}
