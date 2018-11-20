package ru.naumen.sd40.log.parser.dataStorage;

import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.dataSet.DataSet;
import ru.naumen.sd40.log.parser.dataSetFactory.DataSetFactory;
import ru.naumen.sd40.log.parser.exceptions.AlreadyProcessedKeyException;

public class DataStorage<TDataSet extends DataSet>
{
    private TDataSet dataSet = null;
    private DBConnector<TDataSet> dbConnector;
    private DataSetFactory<TDataSet> factory;
    private long currentKey = -1;

    public DataStorage(DBConnector<TDataSet> dbConnector, DataSetFactory<TDataSet> factory)
    {
        this.dbConnector = dbConnector;
        this.factory = factory;
    }

    public void initConnector(String dbName, boolean traceResult)
    {
        dbConnector.init(dbName, traceResult);
    }

    public TDataSet get(long key) throws AlreadyProcessedKeyException
    {
        if (key == currentKey)
        {
            return dataSet;
        }
        if (key > currentKey)
        {
            if (currentKey != -1)
            {
                uploadDataSet();
            }
            currentKey = key;
            dataSet = factory.create();
            return dataSet;
        }
        throw new AlreadyProcessedKeyException();
    }

    public void close()
    {
        uploadDataSet();
    }

    private void uploadDataSet()
    {
        dbConnector.store(currentKey, dataSet);
    }
}

