package ru.naumen.sd40.log.parser.dataStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.dataSet.GCDataSet;
import ru.naumen.sd40.log.parser.dataSetFactory.DataSetFactory;

@Component
public class GCDataStorage extends DataStorage<GCDataSet>
{
    @Autowired
    public GCDataStorage(DBConnector<GCDataSet> dbConnector, DataSetFactory<GCDataSet> factory)
    {
        super(dbConnector, factory);
    }
}
