package ru.naumen.sd40.log.parser.timeParserFactory;

import ru.naumen.sd40.log.parser.timeParser.TimeParser;

public interface TimeParserFactory
{
    TimeParser create(String timeZone, String log);
}
