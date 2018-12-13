package ru.naumen.sd40.log.parser.timeParserFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.timeParser.SDNGTimeParser;
import ru.naumen.sd40.log.parser.timeParser.TimeParser;

import static com.google.common.base.Strings.isNullOrEmpty;

@Component("sdngTimeParser")
@Scope("request")
public class SDNGTimeParserFactory extends CachingTimeParserFactory
{
    @Override
    public TimeParser createAndCache(String timeZone, String log)
    {
        return isNullOrEmpty(timeZone) ? new SDNGTimeParser() : new SDNGTimeParser(timeZone);
    }
}
