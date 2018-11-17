package ru.naumen.sd40.log.parser.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataParser.ActionDoneParser;
import ru.naumen.sd40.log.parser.dataParser.DataSet;
import ru.naumen.sd40.log.parser.dataParser.ErrorParser;

@Component("sdngPopulator")
public class SDNGDataSetPopulator implements DataSetPopulator
{
    private final ActionDoneParser actionDoneParser;
    private final ErrorParser errorParser;

    @Autowired
    public SDNGDataSetPopulator(ActionDoneParser actionDoneParser, ErrorParser errorParser) {
        this.actionDoneParser = actionDoneParser;
        this.errorParser = errorParser;
    }

    @Override
    public void populate(String line, DataSet dataSet)
    {
        actionDoneParser.parseLine(line, dataSet.getActionsDone());
        errorParser.parseLine(line, dataSet.getErrors());
    }
}
