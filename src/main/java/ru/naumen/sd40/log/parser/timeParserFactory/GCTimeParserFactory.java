package ru.naumen.sd40.log.parser.timeParserFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.timeParser.GCTimeParser;
import ru.naumen.sd40.log.parser.timeParser.TimeParser;

import static com.google.common.base.Strings.isNullOrEmpty;

@Component("gcTimeParser")
@Scope("request")
public class GCTimeParserFactory extends CachingTimeParserFactory
{
    @Override
    public TimeParser createAndCache(String timeZone, String log)
    {
        return isNullOrEmpty(timeZone) ? new GCTimeParser() : new GCTimeParser(timeZone);
    }
}
