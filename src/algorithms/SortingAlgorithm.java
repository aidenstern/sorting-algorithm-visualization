package algorithms;

import main.SortArray;

public abstract class SortingAlgorithm {

    public abstract String getName();

    public abstract long getDelay();

    public abstract void setDelay(int delay);

    public abstract void runSort(SortArray list);

}
