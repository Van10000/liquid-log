package ru.naumen.sd40.log.parser.populator;

import ru.naumen.ApplicationContextProvider.ApplicationContextProvider;
import ru.naumen.sd40.log.parser.dataParser.DataSet;
import ru.naumen.sd40.log.parser.dataParser.GCParser;

public class GCDataSetPopulator implements DataSetPopulator
{
    private GCParser parser = ApplicationContextProvider.getContext().getBean(GCParser.class);

    @Override
    public void populate(String line, DataSet dataSet) {
        parser.parseLine(line, dataSet.getGc());
    }
}
