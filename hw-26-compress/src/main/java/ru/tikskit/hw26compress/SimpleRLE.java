package ru.tikskit.hw26compress;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * реализация сжатия по алгоритму простого RLE
 */
public class SimpleRLE implements Compressor {

    @Override
    public byte[] compress(byte[] data) {
        if (data.length == 0) {
            return new byte[0];
        }

        byte[] buff = new byte[data.length * 2];
        int buffSize = 0;

        int seqLen = 1; // длина последовательности одинаковых байтов
        byte curVal = data[0]; // Текущий байт
        int pos = 1; // текущая позиция в массиве байт
        while (pos < data.length) {
            if (data[pos] == curVal && seqLen < 256) {
                seqLen++;
            } else {
                buff[buffSize++] = (byte)seqLen;
                buff[buffSize++] = curVal;
                curVal = data[pos];
                seqLen = 1;
            }
            pos++;
        }

        buff[buffSize++] = (byte)seqLen;
        buff[buffSize++] = curVal;

        byte[] res = new byte[buffSize];
        System.arraycopy(buff, 0, res, 0, buffSize);

        return res;
    }

    @Override
    public byte[] uncompress(byte[] data) {
        if (data.length == 0) {
            return new byte[0];
        }

        LinkedList<Byte> res = new LinkedList<>();
        int pos = 0;
        int resSize = 0;
        while (pos < data.length) {
            int seqLength = data[pos++] & 0xFF;
            byte val = data[pos++];
            resSize += seqLength;
            for (int i = 0; i < seqLength; i++) {
                res.addLast(val);
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
