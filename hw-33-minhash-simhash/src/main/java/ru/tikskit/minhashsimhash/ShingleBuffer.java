package ru.tikskit.minhashsimhash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Буффер, в который передается строка, полученная из файла
 */
public class ShingleBuffer {
    private static final int SHINGLE_SIZE = 9;

    private StringBuilder strBuffer = new StringBuilder();
    /**
     * Слова, которые мы выделили из строки buffer, но их количество недостаточно для создания нового шингла
     */
    private final LinkedList<String> wordsBuffer;
    private final Consumer<List<Shingle>> shinglesConsumer;

    public ShingleBuffer(Consumer<List<Shingle>> shinglesConsumer) {
        wordsBuffer = new LinkedList<>();
        this.shinglesConsumer = shinglesConsumer;
    }

    /**
     * Возвращает все слова, которые можно получить из строки buffer и сокращает эту строку
     * В результат никогда не входит последнее слово из buffer, потому что это может быть только часть слова, а не целое
     */
    private String[] getWords() {
        String string = strBuffer.toString();
        String[] allWords = string.split("[\\s\\r\\n\\t]+");
        String[] res;
        if (allWords.length > 1) {
            res = new String[allWords.length - 1];
            System.arraycopy(allWords, 0, res, 0, allWords.length - 1); // Копируем все слова, кроме последнего
            strBuffer = new StringBuilder(allWords[allWords.length - 1]);
        } else {
            res = new String[0];
        }

        return res;
    }

    /**
     * Извлекает из строки strBuffer все возможные слова и помещает их в список wordsBuffer
     */
    private void strBufferToWordsBuffer() {
        String[] words = getWords();
        if (words.length > 0) {
            wordsBuffer.addAll(Arrays.asList(words));
        }
    }

    /**
     * Из слов wordsBuffer создает шинглы. Использованные слова из wordsBuffer удаляются.
     * Созданные шинглы отправляются shinglesConsumer.
     */
    private void wordsBufferToShingles() {
        String[] shingleWords = new String[SHINGLE_SIZE];
        List<Shingle> shingles = new ArrayList<>();
        while (wordsBuffer.size() >= SHINGLE_SIZE) {
            for (int i = 0; i < SHINGLE_SIZE; i++) {
                shingleWords[i] = wordsBuffer.get(i);
            }
            wordsBuffer.removeFirst();
            shingles.add(new Shingle(shingleWords));
        }

        if (!shingles.isEmpty()) {
            shinglesConsumer.accept(shingles);
        }
    }

    /**
     * Добавление строки в буффер
     */
    public void append(String str) {
        strBuffer.append(str);
        strBufferToWordsBuffer();
        wordsBufferToShingles();
    }

    public void done() {
        strBufferToWordsBuffer();
        if (!strBuffer.isEmpty()) {
            wordsBuffer.addLast(strBuffer.toString());
            wordsBufferToShingles();
        }
    }
}
