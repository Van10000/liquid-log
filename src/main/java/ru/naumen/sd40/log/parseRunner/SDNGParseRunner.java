package ru.naumen.sd40.log.parseRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataStorage.DataStorage;
import ru.naumen.sd40.log.parser.LogParser;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;

@Component
public class SDNGParseRunner extends ParseRunner<SDNGDataSet>
{
    @Autowired
    public SDNGParseRunner(DataStorage<SDNGDataSet> dataStorage, LogParser<SDNGDataSet> logParser)
    {
        super(dataStorage, logParser);
    }
}
