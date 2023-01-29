package ru.tikskit.hw13hashfuncnntables;

public class HashTable<K, V> {

    private static final int DEFAULT_CAPACITY = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private static class KeyValue<K, V> {
        private final K key;
        private V value;
        private KeyValue<K, V> next;

        public KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int threshold;
    private int size;

    public int getSize() {
        return size;
    }

    /**
     * Ведра
     */
    private KeyValue<?, ?>[] buckets;

    public HashTable() {
        int capacity = DEFAULT_CAPACITY;
        this.threshold = (int) (capacity * DEFAULT_LOAD_FACTOR);
        this.buckets = new KeyValue<?, ?>[capacity];
    }

    /**
     * Содержится ли значение
     */
    public boolean contains(V value) {

        for (int b = buckets.length - 1; b >= 0; b--) {
            for (KeyValue<?, ?> e = buckets[b]; e != null; e = e.next) {
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    private int getBucketIndex(K key) {
        int hash = key.hashCode();
        int bucketIndex = hash % buckets.length;
        return bucketIndex < 0 ? -bucketIndex : bucketIndex;
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        KeyValue<K, V> e = (KeyValue<K, V>) buckets[bucketIndex];
        while (e != null) {
            if (e.key.equals(key)) {
                return e.value;
            }
            e = e.next;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private void putInBucket(int bucketIndex, KeyValue<K, V> e) {
        // Новый элемент поместим прямо в buckets
        e.next = (KeyValue<K, V>) buckets[bucketIndex];
        buckets[bucketIndex] = e;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        KeyValue<?, ?>[] oldBuckets = buckets;
        int newCapacity = buckets.length * 2 + 1;
        threshold = (int) (newCapacity * DEFAULT_LOAD_FACTOR);

        buckets = new KeyValue[newCapacity];

        for (int i = oldBuckets.length - 1; i >= 0; i--) {
            KeyValue<?, ?> e = oldBuckets[i];
            while (e != null) { // Мы должны определить новый бакет для всех элементов из всех цепочек
                int bucketIndex = getBucketIndex((K) e.key);
                putInBucket(bucketIndex, (KeyValue<K, V>) e);

                KeyValue<?, ?> next = e.next;
                e.next = null; // У обработанного элемента next обнулим
                e = next; // Обработаем следующий элмент в цепочке
            }
        }
    }

    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        KeyValue<K, V> e = (KeyValue<K, V>) buckets[bucketIndex];
        while (e != null) {
            if (e.key.equals(key)) {
                V r = e.value;
                e.value = value;
                return r;
            } else {
                e = e.next;
            }
        }

        if (++size > threshold) {
            rehash();
            bucketIndex = getBucketIndex(key);
        }
        putInBucket(bucketIndex, new KeyValue<>(key, value));

        return null;
    }

    @SuppressWarnings("unchecked")
    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        KeyValue<K, V> e = (KeyValue<K, V>) buckets[bucketIndex];
        KeyValue<K, V> last = null;
        while (e != null) {
            if (e.key.equals(key)) {
                if (last == null) {
                    buckets[bucketIndex] = e.next;
                } else {
                    last = e.next;
                }
                size--;
                return;
            }
            last = e;
            e = e.next;
        }
    }
}
