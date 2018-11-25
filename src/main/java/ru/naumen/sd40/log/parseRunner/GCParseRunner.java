package ru.naumen.sd40.log.parseRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.LogParser;
import ru.naumen.sd40.log.parser.dataSet.GCDataSet;
import ru.naumen.sd40.log.parser.dataStorage.DataStorage;

@Component
public class GCParseRunner extends ParseRunner<GCDataSet>
{
    @Autowired
    @Lazy
    public GCParseRunner(DataStorage<GCDataSet> dataStorage, LogParser<GCDataSet> logParser,
                         DBConnector<GCDataSet> dbConnector)
    {
        super(dataStorage, logParser, dbConnector);
    }
}
