package ru.tikskit.hw09linearsort;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Ведерная сортировка
 */
public class BucketSort extends AbstractSort implements Sort {


    public BucketSort(int[] data) {
        super(data);
    }

    private int getMaxValue() {
        int res = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > res) {
                res = data[i];
            }
        }

        return res;
    }

    private int getBucketIndex(int value, int maxValue) {
        return value * data.length / (maxValue + 1);
    }

    private LinkedList<Integer>[] createBuckets(int count) {
        LinkedList<Integer>[] res = new LinkedList[count];
        for (int i = 0; i < count; i++) {
            res[i] = new LinkedList<>();
        }
        return res;
    }

    private void putValueToBucket(LinkedList<Integer> bucket, int value) {
        int i = 0;
        // Использование итератора в LinkedList предпочтительнее, чем метод get, поскольку с get придется за каждым
        // последующим элементом бегать от начала списка
        Iterator<Integer> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() > value) {
                // Для сохранения стабильности сортировки, мы находим первое значение, которое больше вставляемого
                // и всавляем прямо перед ним
                bucket.add(i, value);
                return;
            }
            i++;
        }

        bucket.add(value);
    }

    @Override
    public void sort() {
        if (data.length == 0) {
            return;
        }

        int maxValue = getMaxValue();
        LinkedList<Integer>[] buckets = createBuckets(data.length);

        for (int i = 0; i < data.length; i++) {
            int bucketIndex = getBucketIndex(data[i], maxValue);
            putValueToBucket(buckets[bucketIndex], data[i]);
        }

        int resIx = 0;
        for (int b = 0; b < data.length; b++) {
            // Использование итератора в LinkedList предпочтительнее, чем метод get, поскольку с get придется за каждым
            // последующим элементом бегать от начала списка
            Iterator<Integer> iterator = buckets[b].iterator();
            while (iterator.hasNext()) {
                data[resIx++] = iterator.next();
            }
        }
    }
}
