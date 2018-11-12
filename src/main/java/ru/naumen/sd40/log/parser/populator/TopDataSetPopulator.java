package ru.naumen.sd40.log.parser.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataParser.DataSet;
import ru.naumen.sd40.log.parser.dataParser.TopParser;

@Component("topPopulator")
public class TopDataSetPopulator implements DataSetPopulator
{
    private final TopParser parser;

    @Autowired
    public TopDataSetPopulator(TopParser parser) {
        this.parser = parser;
    }

    @Override
    public void populate(String line, DataSet dataSet)
    {
        parser.parseLine(line, dataSet.getTop());
    }
}
