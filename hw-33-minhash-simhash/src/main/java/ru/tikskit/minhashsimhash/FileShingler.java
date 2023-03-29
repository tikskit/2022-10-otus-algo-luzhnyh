package ru.tikskit.minhashsimhash;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Класс, разбирающий файл на шинглы
 */
public class FileShingler implements AutoCloseable {
    private static final int BUFFER_CAPACITY = 256; // Средняя длина слова в русском языке 7,2 буквы, количество слов в шингле 9. Получаем 9 * 7,2 + 2 = 66,8. Округляем до 2^8
    private static final int SHINGLE_SIZE = 9;
    private final Consumer<Set<Shingle>> shinglesConsumer;
    private final StringBuffer stringBuffer;
    private final ShingleBuffer shingleBuffer = new ShingleBuffer(SHINGLE_SIZE);

    private final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
    private final AsynchronousFileChannel fileChannel;
    private final CompletionHandler<Integer, ByteBuffer> completionHandler = new CompletionHandler<>() {

        private int lastPosition = 0;

        @Override
        public void completed(Integer readBytes, ByteBuffer attachment) {
            if (readBytes > 0) {
                byte[] destArray = new byte[readBytes];
                attachment.flip();
                attachment.get(destArray, 0, readBytes);
                stringBuffer.append(destArray);
                System.out.println("test");
                shingleBuffer.append(stringBuffer.getString());

                lastPosition += readBytes;
                buffer.clear();
                fileChannel.read(buffer, lastPosition, buffer, completionHandler);
            }
            Set<Shingle> shingles = shingleBuffer.getShingles();
            shinglesConsumer.accept(shingles);
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            System.err.println(exc);
        }
    };

    public FileShingler(Consumer<Set<Shingle>> shinglesConsumer, Path fileSrc, Charset fileCharset) throws IOException {
        this.shinglesConsumer = shinglesConsumer;
        stringBuffer = new StringBuffer(fileCharset);
        fileChannel = AsynchronousFileChannel.open(fileSrc, StandardOpenOption.READ);
    }

    @Override
    public void close() throws Exception {
        fileChannel.close();
    }

    /**
     * Разобрать файл на шинглы
     */
    public void processFile() {
        fileChannel.read(buffer, 0, buffer, completionHandler);
    }
}
