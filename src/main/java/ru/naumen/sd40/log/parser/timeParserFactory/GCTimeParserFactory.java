package ru.naumen.sd40.log.parser.timeParserFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.StringUtils;
import ru.naumen.sd40.log.parser.timeParser.GCTimeParser;
import ru.naumen.sd40.log.parser.timeParser.TimeParser;

@Component("gcTimeParser")
@Scope("request")
public class GCTimeParserFactory extends CachingTimeParserFactory
{
    @Override
    public TimeParser createAndCache(String timeZone, String log)
    {
        return StringUtils.IsNullOrEmpty(timeZone) ? new GCTimeParser() : new GCTimeParser(timeZone);
    }
}
