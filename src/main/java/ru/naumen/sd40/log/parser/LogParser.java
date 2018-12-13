package ru.naumen.sd40.log.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataSet.DataSet;
import ru.naumen.sd40.log.parser.dataStorage.DataStorage;
import ru.naumen.sd40.log.parser.exceptions.AlreadyProcessedKeyException;
import ru.naumen.sd40.log.parser.populator.DataSetPopulator;
import ru.naumen.sd40.log.parser.timeParser.TimeParser;
import ru.naumen.sd40.log.parser.timeParserFactory.TimeParserFactory;

import static ru.naumen.sd40.log.parser.NumberUtils.floorToClosestMultiple;

/**
 * Created by doki on 22.10.16.
 */

@Component("logParser")
@Lazy
public class LogParser<TDataSet extends DataSet>
{
    public static final long TIME_ALIGNMENT = 5 * 60 * 1000;
    public static final int READER_BUFFER_SIZE = 32 * 1024 * 1024;

    public void parseAndUpload(String logPath, String timezone,
                               DataStorage<TDataSet> storage, TimeParserFactory timeParserFactory,
                               DataSetPopulator<TDataSet> populator)
            throws IOException, ParseException, LogFormatException
    {

        TimeParser timeParser = timeParserFactory.create(timezone, logPath);

        try (BufferedReader br = new BufferedReader(new FileReader(logPath), READER_BUFFER_SIZE))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                Optional<Long> time = timeParser.parse(line);

                if (time.isPresent())
                {
                    long key = floorToClosestMultiple(time.get(), TIME_ALIGNMENT);
                    populator.populate(line, storage.get(key));
                }
            }
        } catch (AlreadyProcessedKeyException e) {
            throw new LogFormatException("Log file has incorrect format: log lines are not ordered by timeParser.");
        }
        storage.close();
    }
}
