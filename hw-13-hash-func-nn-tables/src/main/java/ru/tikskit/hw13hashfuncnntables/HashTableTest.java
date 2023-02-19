package ru.tikskit.hw13hashfuncnntables;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class HashTableTest {
    public static void main(String[] args) {
        testPopulate();
        testPopulateWithDoubles();
        testRemove();
        testRemoveNotExistingValue();
    }

    private static void testPopulate() {
        Map<Integer, Integer> data = IntStream.range(0, 20)
                .boxed()
                .collect(toMap(Integer::intValue, Integer::intValue));

        HashTable<Integer, Integer> hashTable = new HashTable<>();
        data.keySet().forEach(
                k -> hashTable.put(k, data.get(k))
        );
        if (data.values().size() != hashTable.getSize()) {
            throw new IllegalStateException("Не совпадает размер");
        }

        data.values().forEach(
                v -> {
                    if (!hashTable.contains(v)) {
                        throw new IllegalStateException(String.format("Не найдено значение {}", v));
                    }
                }
        );
        data.keySet().forEach(
                k -> {
                    if (hashTable.get(k) == null) {
                        throw new IllegalStateException(String.format("Не найдено значение {}", k));
                    }
                }
        );

    }

    private static void testPopulateWithDoubles() {
        Map<Integer, Integer> data = IntStream.range(0, 20)
                .boxed()
                .collect(toMap(Integer::intValue, Integer::intValue));

        HashTable<Integer, Integer> hashTable = new HashTable<>();
        data.keySet().forEach(
                k -> hashTable.put(k, data.get(k))
        );
        data.keySet().forEach(
                k -> hashTable.put(k, data.get(k) * 1000) // проверим, что новое значение заменяет старое
        );
        if (data.values().size() != hashTable.getSize()) {
            throw new IllegalStateException("Не совпадает размер");
        }

        data.values().forEach(
                v -> {
                    if (!hashTable.contains(v * 1000)) {
                        throw new IllegalStateException(String.format("Не содержится значение {}", v));
                    }
                }
        );
        data.keySet().forEach(
                k -> {
                    Integer value = hashTable.get(k);
                    if (value == null) {
                        throw new IllegalStateException(String.format("Не найдено значение {}", k));
                    }
                    if (data.get(k) * 1000 != value) {
                        throw new IllegalStateException(String.format("Для ключа {} содержится неправильное значение", k));

                    }
                }
        );
    }

    private static void testRemove() {
        Map<Integer, Integer> data = IntStream.range(0, 20)
                .boxed()
                .collect(toMap(Integer::intValue, Integer::intValue));
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        data.keySet().forEach(
                k -> hashTable.put(k, data.get(k))
        );

        data.keySet().forEach(
                k -> {
                    int prevSize = hashTable.getSize();
                    hashTable.remove(k);
                    Integer value = hashTable.get(k);
                    if (value != null) {
                        throw new IllegalStateException(String.format("Значение ключа {} не удалено", k));
                    }

                    if (hashTable.getSize() != prevSize - 1) {
                        throw new IllegalStateException("Неправильно уменьшается размер таблицы");
                    }
                }
        );
    }

    private static void testRemoveNotExistingValue() {
        Map<Integer, Integer> data = IntStream.range(0, 20)
                .boxed()
                .collect(toMap(Integer::intValue, Integer::intValue));
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        data.keySet().forEach(
                k -> hashTable.put(k, data.get(k))
        );
        int prevSize = hashTable.getSize();
        hashTable.remove(10002);
        if (hashTable.getSize() != prevSize) {
            throw new IllegalStateException("Было изменен размер таблицы");
        }
        // все значения остались на месте
        data.keySet().forEach(
                k -> {
                    if (hashTable.get(k) == null) {
                        throw new IllegalStateException(String.format("Не найдено значение {}", k));
                    }
                }
        );
    }
}
