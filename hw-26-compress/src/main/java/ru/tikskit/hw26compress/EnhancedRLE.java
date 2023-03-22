package ru.tikskit.hw26compress;

import java.util.LinkedList;

/**
 * реализация сжатия по алгоритму улучшенного RLE
 */
public class EnhancedRLE implements Compressor {

    private static class Cmp {
        private static final byte MIN_BYTES_COUNT_SEQUENCE = 3;

        private final byte[] data;
        /**
         * Индекс первого байта последовательности повторяющихся байт
         */
        private int seqStartIndex = 0;
        /**
         * Длина последовательности повторяющихся байт
         */
        private int seqLength = 1;
        /**
         * Индекс первого байта последовательности неповторяющихся байт
         */
        private int arbitrarySeqStartIndex = 0;
        /**
         * Количество байт последовательности неповторяющихся байт
         */
        private int arbitrarySeqLength = 1;
        /**
         * Текущая позиция обработки массива data
         */
        private int pos;
        /**
         * Последнее значение из массива
         */
        private byte lastValue;

        /**
         * Результирующий массив байт, со сжатыми данными
         */
        private final byte[] resBuffer;
        /**
         * Размер результирующего массива байт
         */
        private int resBufferSize = 0;

        public Cmp(byte[] data) {
            this.data = data;
            pos = 0;
            if (data.length > 0) {
                resBuffer = new byte[data.length * 2];
                lastValue = data[pos++];
            } else {
                resBuffer = new byte[0];
            }
        }

        /**
         * Если существует последовательность неповторяющихся байтов, то добавляем ее в результат и обрываем.
         */
        private void stopArbitrarySeq() {
            if (arbitrarySeqLength > 1) {
                // Не включаем байты, которые входят в последовательность повторяющихся символов
                if (seqLength >= MIN_BYTES_COUNT_SEQUENCE) {
                    arbitrarySeqLength -= (MIN_BYTES_COUNT_SEQUENCE - 1);
                }
                if (arbitrarySeqLength > 1) {
                    resBuffer[resBufferSize++] = (byte) (-arbitrarySeqLength);
                    System.arraycopy(data, arbitrarySeqStartIndex, resBuffer, resBufferSize, arbitrarySeqLength);
                    resBufferSize += arbitrarySeqLength;
                }
                arbitrarySeqStartIndex = pos;
                arbitrarySeqLength = 1;
            }
        }

        private void flushSeqIfNeeded() {
            if (seqLength > Byte.MAX_VALUE) {
                seqLength = Byte.MAX_VALUE;

                resBuffer[resBufferSize++] = Byte.MAX_VALUE;
                System.arraycopy(data, seqStartIndex, resBuffer, resBufferSize, seqLength);
                resBufferSize += seqLength;

                seqStartIndex += seqLength;
                seqLength = 1;

                arbitrarySeqStartIndex = seqStartIndex;
                arbitrarySeqLength = 1;
            }
        }

        private void stopSequence() {
            if (seqLength >= MIN_BYTES_COUNT_SEQUENCE) {
                resBuffer[resBufferSize++] = (byte) seqLength;
                System.arraycopy(data, seqStartIndex, resBuffer, resBufferSize, 1);
                resBufferSize++;
                arbitrarySeqStartIndex = pos;
                arbitrarySeqLength = 1;
            }
            seqStartIndex = pos;
            seqLength = 1;
        }

        private void flushArbitrarySeqIfNeeded() {
            if (arbitrarySeqLength > Byte.MAX_VALUE) {
                arbitrarySeqLength = Byte.MAX_VALUE;

                resBuffer[resBufferSize++] = -Byte.MAX_VALUE;
                System.arraycopy(data, arbitrarySeqStartIndex, resBuffer, resBufferSize, arbitrarySeqLength);
                resBufferSize += arbitrarySeqLength;

                arbitrarySeqStartIndex += arbitrarySeqLength;
                arbitrarySeqLength = 1;

                seqStartIndex = arbitrarySeqStartIndex;
                seqLength = arbitrarySeqLength;
            }
        }

        private boolean next() {
            if (pos >= data.length) {
                return false;
            }

            if (data[pos] == lastValue) {
                seqLength++;
                if (seqLength == 1) {
                    seqStartIndex = pos;
                }
                if (seqLength < MIN_BYTES_COUNT_SEQUENCE) {
                    arbitrarySeqLength++;
                    flushArbitrarySeqIfNeeded();
                } else if (seqLength == MIN_BYTES_COUNT_SEQUENCE) {
                    stopArbitrarySeq();
                } else {
                    flushSeqIfNeeded();
                }
            } else {
                arbitrarySeqLength++;

                if (arbitrarySeqLength == 1) {
                    arbitrarySeqStartIndex = pos;
                }
                stopSequence();
                flushArbitrarySeqIfNeeded();
            }

            lastValue = data[pos++];
            return true;
        }

        public byte[] compress() {
            while (next()) {}

            if (seqLength >= MIN_BYTES_COUNT_SEQUENCE) {
                stopSequence();
            } else if (arbitrarySeqLength > 1) {
                stopArbitrarySeq();
            }

            byte[] out = new byte[resBufferSize];
            System.arraycopy(resBuffer, 0, out, 0, resBufferSize);
            return out;
        }
    }

    @Override
    public byte[] compress(byte[] data) {
        if (data.length == 0) {
            return new byte[0];
        }

        Cmp cmp = new Cmp(data);
        return cmp.compress();
    }

    @Override
    public byte[] decompress(byte[] data) {
        if (data.length == 0) {
            return new byte[0];
        }

        LinkedList<Byte> res = new LinkedList<>();
        int pos = 0;
        int resSize = 0;
        while (pos < data.length) {
            byte len = data[pos++];
            if (len < 0) {
                for (byte i = 0; i < -len; i++) {
                    byte val = data[pos++];
                    res.addLast(val);
                }
                resSize += (-len);
            } else {
                byte val = data[pos++];
                for (byte i = 0; i < len; i++) {
                    res.addLast(val);
                }
                resSize += len;
            }
        }

        byte[] out = new byte[resSize];
        int i = 0;
        for (Byte b : res) {
            out[i++] = b;
        }

        return out;
    }
}
