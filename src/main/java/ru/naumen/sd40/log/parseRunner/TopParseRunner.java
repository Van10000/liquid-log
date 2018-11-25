package ru.naumen.sd40.log.parseRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.LogParser;
import ru.naumen.sd40.log.parser.dataSet.TopDataSet;
import ru.naumen.sd40.log.parser.dataStorage.DataStorage;

@Component
public class TopParseRunner extends ParseRunner<TopDataSet>
{
    @Autowired
    @Lazy
    public TopParseRunner(DataStorage<TopDataSet> dataStorage, LogParser<TopDataSet> logParser,
                          DBConnector<TopDataSet> dbConnector)
    {
        super(dataStorage, logParser, dbConnector);
    }
}
