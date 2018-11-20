package ru.naumen.sd40.log.parser.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataParser.TopParser;
import ru.naumen.sd40.log.parser.dataSet.TopDataSet;

@Component("topPopulator")
public class TopDataSetPopulator implements DataSetPopulator<TopDataSet>
{
    private final TopParser parser;

    @Autowired
    public TopDataSetPopulator(TopParser parser)
    {
        this.parser = parser;
    }

    @Override
    public void populate(String line, TopDataSet dataSet)
    {
        parser.parseLine(line, dataSet.getTop());
    }
}
