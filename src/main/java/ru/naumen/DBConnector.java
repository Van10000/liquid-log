package ru.naumen;

import ru.naumen.sd40.log.parser.dataParser.DataSet;

public interface DBConnector
{
    void store(long key, DataSet dataSet);
}