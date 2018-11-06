package ru.naumen.sd40.log.parser.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.naumen.sd40.log.parser.dataParser.ActionDoneParser;
import ru.naumen.sd40.log.parser.dataParser.DataSet;
import ru.naumen.sd40.log.parser.dataParser.ErrorParser;

@Configurable
public class SDNGDataSetPopulator implements DataSetPopulator
{
    @Autowired
    private ActionDoneParser actionDoneParser;

    @Autowired
    private ErrorParser errorParser;

    @Override
    public void populate(String line, DataSet dataSet)
    {
        actionDoneParser.parseLine(line, dataSet.getActionsDone());
        errorParser.parseLine(line, dataSet.getErrors());
    }
}
