package ru.naumen.sd40.log.parser.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.naumen.sd40.log.parser.dataParser.DataSet;
import ru.naumen.sd40.log.parser.dataParser.TopParser;

@Configurable
public class TopDataSetPopulator implements DataSetPopulator
{
    @Autowired
    TopParser parser;

    @Override
    public void populate(String line, DataSet dataSet)
    {
        parser.parseLine(line, dataSet.getTop());
    }
}
