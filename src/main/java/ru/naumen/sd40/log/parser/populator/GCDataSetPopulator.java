package ru.naumen.sd40.log.parser.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataParser.DataSet;
import ru.naumen.sd40.log.parser.dataParser.GCParser;

@Component("gcPopulator")
public class GCDataSetPopulator implements DataSetPopulator
{
    private final GCParser parser;

    @Autowired
    public GCDataSetPopulator(GCParser parser) {
        this.parser = parser;
    }

    @Override
    public void populate(String line, DataSet dataSet) {
        parser.parseLine(line, dataSet.getGc());
    }
}
