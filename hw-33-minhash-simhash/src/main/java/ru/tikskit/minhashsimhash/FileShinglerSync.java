package ru.tikskit.minhashsimhash;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Consumer;

/**
 * Класс считывает шингшли из файла синхронным образом
 */
public class FileShinglerSync {
    private static final int BUFFER_CAPACITY = 1024;
    private static final int SHINGLE_SIZE = 9;

    private final String srcFile;
    private final Consumer<List<Shingle>> shinglesConsumer;
    private final StringBuffer stringBuffer;
    private final ShingleBuffer shingleBuffer = new ShingleBuffer(SHINGLE_SIZE);

    public FileShinglerSync(String srcFile, Consumer<List<Shingle>> shinglesConsumer, Charset fileCharset) {
        this.srcFile = srcFile;
        this.shinglesConsumer = shinglesConsumer;
        this.stringBuffer = new StringBuffer(fileCharset);
    }

    private void processBytes(byte[] buff, int readBytes) {
        stringBuffer.append(buff, readBytes);
        shingleBuffer.append(stringBuffer.getString());
        List<Shingle> shingles = shingleBuffer.getShingles();
        if (!shingles.isEmpty()) {
            shinglesConsumer.accept(shingles);
        }
    }

    public void read() throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile))) {
            byte[] buff = new byte[BUFFER_CAPACITY];
            int pos = 0;
            int readBytes;
            do {
                readBytes = bis.read(buff, pos, BUFFER_CAPACITY);
                if (readBytes > 0) {
                    processBytes(buff, readBytes);
                }
            } while (readBytes > 0);
        }
    }
}
