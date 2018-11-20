package ru.naumen.sd40.log.parseRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.LogParser;
import ru.naumen.sd40.log.parser.dataSet.GCDataSet;
import ru.naumen.sd40.log.parser.dataStorage.DataStorage;

@Component
public class GCParseRunner extends ParseRunner<GCDataSet>
{
    @Autowired
    public GCParseRunner(DataStorage<GCDataSet> dataStorage, LogParser<GCDataSet> logParser)
    {
        super(dataStorage, logParser);
    }
}
