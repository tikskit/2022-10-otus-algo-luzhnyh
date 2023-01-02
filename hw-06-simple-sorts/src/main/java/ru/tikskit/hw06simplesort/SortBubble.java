package ru.tikskit.hw06simplesort;

public class SortBubble extends AbstractSort implements Sort {

    public SortBubble(int[] data) {
        super(data);
    }

    @Override
    public void sort() {
        for (int i = data.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }
}
