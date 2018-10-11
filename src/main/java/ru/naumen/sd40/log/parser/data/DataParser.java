package ru.naumen.sd40.log.parser.data;

import ru.naumen.sd40.log.parser.dataset.DataSet;

import java.text.ParseException;

public interface DataParser
{
    void parse(String line, DataSet dataSet) throws ParseException;
}
