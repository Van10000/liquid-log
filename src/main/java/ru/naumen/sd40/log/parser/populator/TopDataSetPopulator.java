package ru.naumen.sd40.log.parser.populator;

import ru.naumen.ApplicationContextProvider.ApplicationContextProvider;
import ru.naumen.sd40.log.parser.dataParser.DataSet;
import ru.naumen.sd40.log.parser.dataParser.TopParser;

public class TopDataSetPopulator implements DataSetPopulator
{
    private TopParser parser = ApplicationContextProvider.getContext().getBean(TopParser.class);

    @Override
    public void populate(String line, DataSet dataSet)
    {
        parser.parseLine(line, dataSet.getTop());
    }
}
