package ru.naumen;

public interface DBConnector<TDataSet>
{
    void store(long key, TDataSet dataSet);

    void init(String dbName, boolean traceResult);
}