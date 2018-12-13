package ru.naumen.sd40.log.parseRunner;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.dataStorage.DataStorage;
import ru.naumen.sd40.log.parser.LogFormatException;
import ru.naumen.sd40.log.parser.LogParser;
import ru.naumen.sd40.log.parser.dataSet.DataSet;
import ru.naumen.sd40.log.parser.populator.DataSetPopulator;
import ru.naumen.sd40.log.parser.timeParserFactory.TimeParserFactory;

import java.io.IOException;
import java.text.ParseException;

@Component("parseRunner")
@Lazy
public class ParseRunner<TDataSet extends DataSet>
{
    private final DataStorage<TDataSet> dataStorage;
    private final LogParser<TDataSet> logParser;
    private final DBConnector dbConnector;
    private BeanFactory beanFactory;

    public ParseRunner(DataStorage<TDataSet> dataStorage, LogParser<TDataSet> logParser,
                       DBConnector dbConnector, BeanFactory beanFactory)
    {
        this.dataStorage = dataStorage;
        this.logParser = logParser;
        this.dbConnector = dbConnector;
        this.beanFactory = beanFactory;
    }

    public void run(String logPath, String timezone, String parseMode, String dbName, boolean traceResult)
            throws ParseException, LogFormatException, IOException
    {
        dbConnector.init(dbName, traceResult);
        DataSetPopulator<TDataSet> populator = (DataSetPopulator<TDataSet>)beanFactory.getBean(parseMode + "Populator");
        TimeParserFactory timeParserFactory = beanFactory.getBean(parseMode + "TimeParser", TimeParserFactory.class);
        logParser.parseAndUpload(logPath, timezone, dataStorage, timeParserFactory, populator);
    }
}

