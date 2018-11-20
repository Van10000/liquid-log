package ru.naumen.sd40.log.parseRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.LogParser;
import ru.naumen.sd40.log.parser.dataSet.TopDataSet;
import ru.naumen.sd40.log.parser.dataStorage.DataStorage;

@Component
public class TopParseRunner extends ParseRunner<TopDataSet>
{
    @Autowired
    public TopParseRunner(DataStorage<TopDataSet> dataStorage, LogParser<TopDataSet> logParser)
    {
        super(dataStorage, logParser);
    }
}
