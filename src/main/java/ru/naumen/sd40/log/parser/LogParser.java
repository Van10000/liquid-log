package ru.naumen.sd40.log.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;

import ru.naumen.sd40.log.parser.data.GCDataSetPopulator;
import ru.naumen.sd40.log.parser.data.DataSetPopulator;
import ru.naumen.sd40.log.parser.data.SDNGDataSetPopulator;
import ru.naumen.sd40.log.parser.data.TopDataSetPopulator;
import ru.naumen.sd40.log.parser.time.GCTimeParser;
import ru.naumen.sd40.log.parser.time.TimeParser;
import ru.naumen.sd40.log.parser.time.SDNGTimeParser;
import ru.naumen.sd40.log.parser.time.TopTimeParser;

import static ru.naumen.sd40.log.parser.NumberUtils.floorToClosestMultiple;

/**
 * Created by doki on 22.10.16.
 */
public class LogParser
{
    public static final long TIME_ALIGNMENT = 5 * 60 * 1000;
    public static final int READER_BUFFER_SIZE = 32 * 1024 * 1024;

    private static HashMap<String, Supplier<TimeParser>> registerTimeParsers(String timeZone, String log)
    {
        HashMap<String, Supplier<TimeParser>> timeParsers = new HashMap<>();
        boolean noTimezone = timeZone == null || timeZone.length() == 0;
        timeParsers.put("sdng", () -> noTimezone ? new SDNGTimeParser() : new SDNGTimeParser(timeZone));
        timeParsers.put("gc", () -> noTimezone ? new GCTimeParser() : new GCTimeParser(timeZone));
        timeParsers.put("top", () -> new TopTimeParser(log));
        return timeParsers;
    }

    private static HashMap<String, Supplier<DataSetPopulator>> registerDataSetPopulators()
    {
        HashMap<String, Supplier<DataSetPopulator>> dataSetPopulators = new HashMap<>();
        dataSetPopulators.put("sdng", SDNGDataSetPopulator::new);
        dataSetPopulators.put("gc", GCDataSetPopulator::new);
        dataSetPopulators.put("top", TopDataSetPopulator::new);
        return dataSetPopulators;
    }

    public static void parseAndUpload(String logPath, String timezone, String mode, DataStorage storage)
            throws IOException, ParseException, LogFormatException
    {
        TimeParser timeParser = registerTimeParsers(timezone, logPath).get(mode).get();
        DataSetPopulator dataSetPopulator = registerDataSetPopulators().get(mode).get();

        try (BufferedReader br = new BufferedReader(new FileReader(logPath), READER_BUFFER_SIZE))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                Optional<Long> time = timeParser.parse(line);

                if (time.isPresent())
                {
                    long key = floorToClosestMultiple(time.get(), TIME_ALIGNMENT);
                    dataSetPopulator.populate(line, storage.get(key));
                }
            }
        } catch (DataStorage.AlreadyProcessedKeyException e) {
            throw new LogFormatException("Log file has incorrect format: log lines are not ordered by time.");
        }
        storage.close();
    }
}
