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

    private final String srcFile;
    private final StringBuffer stringBuffer;
    private final ShingleBuffer shingleBuffer;

    public FileShinglerSync(String srcFile, Consumer<List<Shingle>> shinglesConsumer, Charset fileCharset) {
        this.srcFile = srcFile;
        this.shingleBuffer = new ShingleBuffer(shinglesConsumer);
        this.stringBuffer = new StringBuffer(fileCharset, shingleBuffer::append);
    }

    private void processBytes(byte[] buff, int readBytes) {
        stringBuffer.append(buff, readBytes);
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
        stringBuffer.done();
        shingleBuffer.done();
    }
}
