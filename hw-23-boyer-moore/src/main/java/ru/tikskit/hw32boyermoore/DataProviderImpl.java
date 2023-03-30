package ru.tikskit.hw32boyermoore;

public class DataProviderImpl implements DataProvider {
    @Override
    public Data[] getData() {
        Data[] res = new Data[6];

        res[0] = (new DataGeneratorRandomBlocks()).gen();
        res[1] = (new DataGeneratorMaskNeverMatch()).gen();
        res[2] = (new DataGeneratorMaskMatchButLastCharacter()).gen();
        res[3] = (new DataGeneratorMaskMatchButFirstCharacter()).gen();
        res[4] = (new DataGeneratorTailMatch()).gen();
        res[5] = (new DataGeneratorTailMatchRandom()).gen();
        return res;
    }
}
