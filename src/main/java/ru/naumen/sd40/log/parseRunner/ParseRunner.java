package ru.naumen.sd40.log.parseRunner;

import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.dataStorage.DataStorage;
import ru.naumen.sd40.log.parser.LogFormatException;
import ru.naumen.sd40.log.parser.LogParser;
import ru.naumen.sd40.log.parser.dataSet.DataSet;

import java.io.IOException;
import java.text.ParseException;

public class ParseRunner<TDataSet extends DataSet>
{
    private final DataStorage<TDataSet> dataStorage;
    private final LogParser<TDataSet> logParser;
    private final DBConnector<TDataSet> dbConnector;

    public ParseRunner(DataStorage<TDataSet> dataStorage, LogParser<TDataSet> logParser, DBConnector<TDataSet> dbConnector)
    {
        this.dataStorage = dataStorage;
        this.logParser = logParser;
        this.dbConnector = dbConnector;
    }

    public void run(String logPath, String timezone, String parseMode, String dbName, boolean traceResult)
            throws ParseException, LogFormatException, IOException
    {
        dbConnector.init(dbName, traceResult);
        logParser.parseAndUpload(logPath, timezone, parseMode, dataStorage);
    }
}

