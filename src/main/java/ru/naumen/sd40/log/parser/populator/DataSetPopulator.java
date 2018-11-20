package ru.naumen.sd40.log.parser.populator;

import ru.naumen.sd40.log.parser.dataSet.DataSet;

import java.text.ParseException;

public interface DataSetPopulator<TDataSet extends DataSet>
{
    void populate(String line, TDataSet dataSet) throws ParseException;
}