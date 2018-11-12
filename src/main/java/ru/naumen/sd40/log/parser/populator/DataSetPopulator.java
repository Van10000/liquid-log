package ru.naumen.sd40.log.parser.populator;

import ru.naumen.sd40.log.parser.dataParser.DataSet;

import java.text.ParseException;

public interface DataSetPopulator
{
    void populate(String line, DataSet dataSet) throws ParseException;
}
