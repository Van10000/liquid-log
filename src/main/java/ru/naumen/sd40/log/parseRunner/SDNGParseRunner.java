package ru.naumen.sd40.log.parseRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.dataStorage.DataStorage;
import ru.naumen.sd40.log.parser.LogParser;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;

@Component
public class SDNGParseRunner extends ParseRunner<SDNGDataSet>
{
    @Autowired
    @Lazy
    public SDNGParseRunner(DataStorage<SDNGDataSet> dataStorage, LogParser<SDNGDataSet> logParser,
                           DBConnector<SDNGDataSet> dbConnector)
    {
        super(dataStorage, logParser, dbConnector);
    }
}
