package ru.naumen.sd40.log.parser.data;

import ru.naumen.sd40.log.parser.dataset.DataSet;

public class SDNGDataParser implements DataParser
{
    @Override
    public void parse(String line, DataSet dataSet) {
        dataSet.getActionsDone().parseLine(line);
        dataSet.getErrors().parseLine(line);
    }
}
