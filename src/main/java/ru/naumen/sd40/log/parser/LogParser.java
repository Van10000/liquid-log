package ru.naumen.sd40.log.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataSet.DataSet;
import ru.naumen.sd40.log.parser.exceptions.AlreadyProcessedKeyException;
import ru.naumen.sd40.log.parser.populator.DataSetPopulator;
import ru.naumen.sd40.log.parser.timeParser.GCTimeParser;
import ru.naumen.sd40.log.parser.timeParser.TimeParser;
import ru.naumen.sd40.log.parser.timeParser.SDNGTimeParser;
import ru.naumen.sd40.log.parser.timeParser.TopTimeParser;

import static ru.naumen.sd40.log.parser.NumberUtils.floorToClosestMultiple;

/**
 * Created by doki on 22.10.16.
 */

@Component
public class LogParser<TDataSet extends DataSet>
{
    public static final long TIME_ALIGNMENT = 5 * 60 * 1000;
    public static final int READER_BUFFER_SIZE = 32 * 1024 * 1024;

    private final BeanFactory beanFactory;

    @Autowired
    public LogParser(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    private static HashMap<String, Supplier<TimeParser>> registerTimeParsers(String timeZone, String log)
    {
        HashMap<String, Supplier<TimeParser>> timeParsers = new HashMap<>();
        boolean noTimezone = timeZone == null || timeZone.length() == 0;
        timeParsers.put("sdng", () -> noTimezone ? new SDNGTimeParser() : new SDNGTimeParser(timeZone));
        timeParsers.put("gc", () -> noTimezone ? new GCTimeParser() : new GCTimeParser(timeZone));
        timeParsers.put("top", () -> new TopTimeParser(log));
        return timeParsers;
    }

    public void parseAndUpload(String logPath, String timezone, String mode, DataStorage<TDataSet> storage)
            throws IOException, ParseException, LogFormatException
    {
        TimeParser timeParser = registerTimeParsers(timezone, logPath).get(mode).get();
        DataSetPopulator<TDataSet> populator = (DataSetPopulator<TDataSet>)
                beanFactory.getBean(mode + "Populator");

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
