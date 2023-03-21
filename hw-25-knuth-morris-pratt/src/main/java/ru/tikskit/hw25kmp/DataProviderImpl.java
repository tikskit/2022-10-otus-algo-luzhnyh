package ru.tikskit.hw25kmp;

public class DataProviderImpl implements DataProvider {
    @Override
    public Data[] getData() {
        Data[] res = new Data[4];

        res[0] = (new DataGeneratorRandomBlocks()).gen();
        res[1] = (new DataGeneratorMaskNeverMatch()).gen();
        res[2] = (new DataGeneratorMaskMatchButLastCharacter()).gen();
        res[3] = (new DataGeneratorMaskMatchButFirstCharacter()).gen();
        return res;
    }
}
