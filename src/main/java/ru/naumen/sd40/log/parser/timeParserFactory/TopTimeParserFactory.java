package ru.naumen.sd40.log.parser.timeParserFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.timeParser.TimeParser;
import ru.naumen.sd40.log.parser.timeParser.TopTimeParser;

@Component("topTimeParser")
@Scope("request")
public class TopTimeParserFactory extends CachingTimeParserFactory
{
    @Override
    public TimeParser createAndCache(String timeZone, String log)
    {
        return new TopTimeParser(log);
    }
}
