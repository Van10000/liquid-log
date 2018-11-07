package ru.naumen.sd40.log.parser.populator;

import ru.naumen.ApplicationContextProvider.ApplicationContextProvider;
import ru.naumen.sd40.log.parser.dataParser.ActionDoneParser;
import ru.naumen.sd40.log.parser.dataParser.DataSet;
import ru.naumen.sd40.log.parser.dataParser.ErrorParser;

public class SDNGDataSetPopulator implements DataSetPopulator
{
    private ActionDoneParser actionDoneParser = ApplicationContextProvider.getContext().getBean(ActionDoneParser.class);
    private ErrorParser errorParser = ApplicationContextProvider.getContext().getBean(ErrorParser.class);

    @Override
    public void populate(String line, DataSet dataSet)
    {
        actionDoneParser.parseLine(line, dataSet.getActionsDone());
        errorParser.parseLine(line, dataSet.getErrors());
    }
}
