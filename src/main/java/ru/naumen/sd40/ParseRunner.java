package ru.naumen.sd40;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataStorage;
import ru.naumen.sd40.log.parser.LogFormatException;
import ru.naumen.sd40.log.parser.LogParser;
import ru.naumen.sd40.log.parser.dataSet.DataSet;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;

import java.io.IOException;
import java.text.ParseException;

@Component
public class ParseRunner<TDataSet extends DataSet>
{
    private final DataStorage<TDataSet> dataStorage;
    private final LogParser<TDataSet> logParser;

    @Autowired
    public ParseRunner(DataStorage<TDataSet> dataStorage, LogParser<TDataSet> logParser)
    {
        this.dataStorage = dataStorage;
        this.logParser = logParser;
    }

    public void run(String logPath, String timezone, String parseMode, String dbName, boolean traceResult)
            throws ParseException, LogFormatException, IOException
    {
        dataStorage.initConnector(dbName, traceResult);
        logParser.parseAndUpload(logPath, timezone, parseMode, dataStorage);
    }
}

