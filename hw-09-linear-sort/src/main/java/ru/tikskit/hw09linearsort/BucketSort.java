package ru.tikskit.hw09linearsort;

/**
 * Ведерная сортировка
 */
public class BucketSort extends AbstractSort implements Sort {

    private static final int BUCKET_COUNT = 10;


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

    private static class Node {
        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private int getBucketIndex(int value, int maxValue) {
        return value * BUCKET_COUNT / (maxValue + 1);
    }

    private void putValueToBucket(Node[] buckets, int bucketIndex, int value) {
        Node cur = buckets[bucketIndex];
        Node prev = null;
        Node newNode = new Node(value);
        while (cur != null) {
            if (cur.value > value) {
                if (prev == null) {
                    buckets[bucketIndex] = newNode;
                } else {
                    prev.next = newNode;
                }
                newNode.next = cur;
                return;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        if (prev == null) {
            buckets[bucketIndex] = newNode;
        } else {
            prev.next = newNode;
        }
    }

    @Override
    public void sort() {
        if (data.length <= 1) {
            return;
        }

        int maxValue = getMaxValue();
        Node[] buckets = new Node[BUCKET_COUNT];

        for (int i = 0; i < data.length; i++) {
            int bucketIndex = getBucketIndex(data[i], maxValue);
            putValueToBucket(buckets, bucketIndex, data[i]);
        }

        int resIx = 0;
        for (int b = 0; b < BUCKET_COUNT; b++) {
            Node cur = buckets[b];
            while (cur != null) {
                data[resIx++] = cur.value;
                cur = cur.next;
            }
        }
    }
}
