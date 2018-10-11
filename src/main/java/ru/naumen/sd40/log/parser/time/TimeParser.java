package ru.naumen.sd40.log.parser.time;

import java.text.ParseException;

public interface TimeParser
{
    long parse(String line) throws ParseException;
}