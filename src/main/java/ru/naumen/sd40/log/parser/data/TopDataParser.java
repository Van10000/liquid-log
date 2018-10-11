package ru.naumen.sd40.log.parser.data;

import ru.naumen.sd40.log.parser.dataset.DataSet;

import java.text.ParseException;

public class TopDataParser implements DataParser
{
    @Override
    public void parse(String line, DataSet dataSet) throws ParseException {
        dataSet.getTop().parseLine(line);
    }
}
