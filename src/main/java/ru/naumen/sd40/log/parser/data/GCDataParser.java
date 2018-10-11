package ru.naumen.sd40.log.parser.data;

import ru.naumen.sd40.log.parser.dataset.DataSet;

public class GCDataParser implements DataParser
{
    @Override
    public void parse(String line, DataSet dataSet) {
        dataSet.getGc().parseLine(line);
    }
}
