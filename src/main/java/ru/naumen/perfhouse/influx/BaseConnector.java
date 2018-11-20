package ru.naumen.perfhouse.influx;

import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.dataSet.DataSet;

abstract class BaseConnector<TDataSet extends DataSet> implements DBConnector<TDataSet>
{
    protected InfluxDAO storage;
    protected String dbName;
    protected boolean traceResult;

    protected BaseConnector(InfluxDAO storage)
    {
        this.storage = storage;
    }

    public void init(String dbName, boolean traceResult)
    {
        storage.init();
        storage.connectToDB(dbName);
        this.dbName = dbName;
        this.traceResult = traceResult;
    }
}
