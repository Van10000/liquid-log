package ru.naumen.sd40.log.parser.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataParser.GCParser;
import ru.naumen.sd40.log.parser.dataSet.GCDataSet;

@Component("gcPopulator")
public class GCDataSetPopulator implements DataSetPopulator<GCDataSet>
{
    private final GCParser parser;

    @Autowired
    public GCDataSetPopulator(GCParser parser)
    {
        this.parser = parser;
    }

    @Override
    public void populate(String line, GCDataSet dataSet)
    {
        parser.parseLine(line, dataSet.getGc());
    }
}
