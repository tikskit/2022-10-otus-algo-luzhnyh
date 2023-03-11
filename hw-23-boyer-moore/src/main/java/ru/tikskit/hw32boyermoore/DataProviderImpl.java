package ru.tikskit.hw32boyermoore;

public class DataProviderImpl implements DataProvider {
    @Override
    public Data[] getData() {
        Data[] res = new Data[3];

        res[0] = (new DataGeneratorRandomBlocks()).gen();
        res[1] = (new DataGeneratorMaskNeverMatch()).gen();
        res[2] = (new DataGeneratorMaskMatchButLastCharacter()).gen();
        return res;
    }
}
