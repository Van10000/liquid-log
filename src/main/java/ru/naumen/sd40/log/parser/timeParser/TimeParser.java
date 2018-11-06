package ru.naumen.sd40.log.parser.timeParser;

import java.text.ParseException;
import java.util.Optional;

public interface TimeParser
{
    Optional<Long> parse(String line) throws ParseException;
}