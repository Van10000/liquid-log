package ru.naumen.sd40.log.parser.dataParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.data.GCData;

@Component
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
