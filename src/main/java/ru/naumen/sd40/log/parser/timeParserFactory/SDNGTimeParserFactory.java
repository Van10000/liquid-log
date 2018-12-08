package ru.naumen.sd40.log.parser.timeParserFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.StringUtils;
import ru.naumen.sd40.log.parser.timeParser.SDNGTimeParser;
import ru.naumen.sd40.log.parser.timeParser.TimeParser;

@Component("sdngTimeParser")
@Scope("request")
public class SDNGTimeParserFactory extends CachingTimeParserFactory
{
    @Override
    public TimeParser createAndCache(String timeZone, String log)
    {
        return StringUtils.IsNullOrEmpty(timeZone) ? new SDNGTimeParser() : new SDNGTimeParser(timeZone);
    }
}
