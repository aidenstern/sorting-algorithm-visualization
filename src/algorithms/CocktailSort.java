package algorithms;

import main.SortArray;

public class CocktailSort extends SortingAlgorithm {

    @Override
    public String getName() {
        return "Cocktail Sort";
    }

    @Override
    public void runSort(SortArray list) {
        boolean swapped = true;
        int start = 0;
        int end = list.size();

        while (swapped) {
            swapped = false;

            for (int i = start; i < end - 1; ++i) {
                if (list.get(i) > list.get(i + 1)) {
                    list.swap(i, i + 1);
                    swapped = true;
                }
            }

            if (!swapped)
                break;
            swapped = false;
            end = end - 1;

            for (int i = end - 1; i >= start; i--) {
                if (list.get(i) > list.get(i + 1)) {
                    list.swap(i, i + 1);
                    swapped = true;
                }
            }

            start = start + 1;
        }
    }
}
