package ru.naumen.sd40.log.parser.dataStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;
import ru.naumen.sd40.log.parser.dataSetFactory.DataSetFactory;

@Component
public class SDNGDataStorage extends DataStorage<SDNGDataSet>
{
    @Autowired
    public SDNGDataStorage(DBConnector<SDNGDataSet> dbConnector, DataSetFactory<SDNGDataSet> factory)
    {
        super(dbConnector, factory);
    }
}
