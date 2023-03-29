package ru.tikskit.minhashsimhash;


import java.nio.charset.Charset;
import java.util.Optional;


/**
 * Буфер, в который можно добавлять байты, считанные из файла и получать строку максимальной длины
 */
public class StringBuffer {
    private static final int BUFFER_SIZE = 1024;
    private final byte[] data = new byte[BUFFER_SIZE];
    private int dataSize = 0;
    private final Charset charset;
    private final StringBuilder strResult = new StringBuilder();

    public StringBuffer(Charset charset) {
        this.charset = charset;
    }

    private void appendData(byte[] data, int length) {
        System.arraycopy(data, 0, this.data, dataSize, length);
        dataSize += length;
    }

    /**
     * Создает и возвращает из данных строку. Обнуляет буфер данных
     */
    private Optional<String> getStringFromData() {
        if (dataSize < 2) {
            return Optional.empty();
        }
        /* char в java занимает 2 байта. Значит, для получения строки из массива байт dataSize мы можем считывать
        максимальное количество байт, кратное 2. */
        int bytesToRead = dataSize / 2 * 2; //Количество байт, кратное 2
        String string = new String(data, 0, bytesToRead, charset);
        /*Если количество байтов в dataSize нечетное, то скопируем оставшийся байт в начало массива и установим его размер в 1*/
        if (bytesToRead < dataSize) {
            System.arraycopy(data, bytesToRead, data, 0, 1);
            dataSize = 1;
        } else {
            dataSize = 0;
        }
        return Optional.of(string);
    }

    public void append(byte[] data, int length) {
        if (length > BUFFER_SIZE) {
            throw new DataSizeException(String.format("Размер данных %s превышает размер буфера %s", length, BUFFER_SIZE));
        }
        appendData(data, length);
        Optional<String> string = getStringFromData();
        string.map(strResult::append);
    }

    public String getString() {
        return strResult.toString();
    }
}
