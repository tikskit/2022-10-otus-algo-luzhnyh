package ru.tikskit.hw26compress;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * реализация сжатия по алгоритму простого RLE
 */
public class SimpleRLE implements Compressor {
    /**
     * Сжимает массив данных
     * @param data входной массив данных
     * @return сжатый массив
     */
    @Override
    public int[] compress(int[] data) {
        if (data.length == 0) {
            return new int[0];
        }

        int[] res = new int[data.length];
        int resSize = 0;

        int seqLen = 1;
        int curVal = data[0];
        int pos = 1;
        while (pos < data.length) {
            if (data[pos] == curVal) {
                seqLen++; /*В этом упрощенном варианте у нас не может быть переполнения Integer, поскольку data это массив, а не поток*/
            } else {
                res[resSize++] = seqLen;
                res[resSize++] = curVal;
                curVal = data[pos];
                seqLen = 1;
            }
            pos++;
        }

        return res;
    }

    @Override
    public int[] uncompress(int[] data) {
        if (data.length == 0) {
            return new int[0];
        }

        LinkedList<Integer> res = new LinkedList<>();
        int pos = 0;
        int resDataLength = 0;
        while (pos < data.length) {
            int seqLength = data[pos++];
            int val = data[pos++];
            resDataLength += seqLength;
            for (int i = 0; i < seqLength; i++) {
                res.addLast(val);
            }
        }

        int[] out = new int[resDataLength];
        int i = 0;
        for (Integer re : res) {
            out[i++] = re;
        }

        return out;
    }
}
