package ru.naumen.sd40.log.parser.dataParser;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.data.ErrorData;

import java.util.regex.Pattern;

/**
 * Created by doki on 22.10.16.
 */
@Component
public class ErrorParser
{
    private Pattern warnRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) WARN");
    private Pattern errorRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) ERROR");
    private Pattern fatalRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) FATAL");

    public void parseLine(String line, ErrorData data)
    {
        if (warnRegEx.matcher(line).find())
        {
            data.increaseWarnCount(1);
        }
        if (errorRegEx.matcher(line).find())
        {
            data.increaseErrorCount(1);
        }
        if (fatalRegEx.matcher(line).find())
        {
            data.increaseFatalCount(1);
        }
    }
}
