package ru.naumen.sd40.log.parser.dataStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.dataSet.TopDataSet;
import ru.naumen.sd40.log.parser.dataSetFactory.DataSetFactory;

@Component
public class TopDataStorage extends DataStorage<TopDataSet>
{
    @Autowired
    @Lazy
    public TopDataStorage(DBConnector<TopDataSet> dbConnector, DataSetFactory<TopDataSet> factory)
    {
        super(dbConnector, factory);
    }
}
