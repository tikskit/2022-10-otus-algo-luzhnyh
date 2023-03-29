package ru.tikskit.minhashsimhash;


import java.nio.charset.Charset;
import java.util.Optional;
import java.util.function.Consumer;


/**
 * Буфер, в который мы напихиваем байты из файла по мере считывания, а когда он заполняется, создает из этих байтов
 * строку и передает в stringConsumer
 */
public class StringBuffer {
    private static final int BUFFER_SIZE = 1024;
    private final byte[] data = new byte[BUFFER_SIZE];
    private int dataSize = 0;
    private final Charset charset;
    private final Consumer<String> stringConsumer;

    public StringBuffer(Charset charset, Consumer<String> stringConsumer) {
        this.charset = charset;
        this.stringConsumer = stringConsumer;
    }

    private void appendData(byte[] data, int startPos, int length) {
        System.arraycopy(data, startPos, this.data, dataSize, length);
        dataSize += length;
    }

    /**
     * Создает и возвращает из данных строку. Очищает буфер с данными
     */
    private Optional<String> getStringFromData() {
        if (dataSize < 2) {
            return Optional.empty();
        }
        /* char в java занимает 2 байта. Значит, для получения строки из массива байт data мы можем считывать только
        количество байт кратное 2. */
        int bytesToRead = dataSize / 2 * 2; //Количество байт, кратное 2
        String string = new String(data, 0, bytesToRead, charset);
        /*Если количество байтов в dataSize нечетное, то скопируем оставшийся байт в начало массива и установим размер
        буфера в 1*/
        if (bytesToRead < dataSize) {
            System.arraycopy(data, bytesToRead, data, 0, 1);
            dataSize = 1;
        } else {
            dataSize = 0;
        }
        return Optional.of(string);
    }

    /**
     * Получить из данных буфера строку и передать ее в stringConsumer
     */
    private void flush() {
        Optional<String> string = getStringFromData();
        string.ifPresent(stringConsumer);
    }

    /**
     * Добавить данные в буфер
     * @param data массив-источник данных
     * @param length количество данных из data
     */
    public void append(byte[] data, int length) {
        if (length == 0) {
            return;
        }
        /*Будем добавлять в буфер данные таким образом, чтобы не происходило его переполнения. А когда он полностью
        * заполнен, то триггерим stringConsumer, передаем в него полученную строку и очищаем буфер*/
        int startPos = 0;
        while (length > 0) {
            int len = Math.min(BUFFER_SIZE - dataSize, length);
            appendData(data, startPos, len);
            startPos += len;
            length -= len;

            if (dataSize == BUFFER_SIZE) {
                flush();
            }
        }
    }

    /**
     * Вызывать этот метод, когда чтения данных из файла закончено.
     * Из данных будет создана строка и отправлена в stringConsumer.
     */
    public void done() {
        flush();
    }
}
