package ru.naumen.sd40.log.parser.timeParserFactory;

import ru.naumen.sd40.log.parser.timeParser.TimeParser;

public abstract class CachingTimeParserFactory implements TimeParserFactory
{
    TimeParser cached = null;

    @Override
    public TimeParser create(String timeZone, String log)
    {
        if (cached != null)
            return cached;

        cached = createAndCache(timeZone, log);
        return cached;
    }

    abstract TimeParser createAndCache(String timeZone, String log);
}
