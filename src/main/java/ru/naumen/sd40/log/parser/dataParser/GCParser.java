package ru.naumen.sd40.log.parser.dataParser;

import static ru.naumen.sd40.log.parser.NumberUtils.getSafeDouble;
import static ru.naumen.sd40.log.parser.NumberUtils.roundToTwoPlaces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import ru.naumen.sd40.log.parser.data.GCData;

public class GCParser
{
    private Pattern gcExecutionTime = Pattern.compile(".*real=(.*)secs.*");

    public void parseLine(String line, GCData data)
    {
        Matcher matcher = gcExecutionTime.matcher(line);
        if (matcher.find())
        {
            data.getDs().addValue(Double.parseDouble(matcher.group(1).trim().replace(',', '.')));
        }
    }
}
