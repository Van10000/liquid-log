package ru.naumen.sd40.log.parser.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataParser.ActionDoneParser;
import ru.naumen.sd40.log.parser.dataParser.ErrorParser;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;

@Component("sdngPopulator")
public class SDNGDataSetPopulator implements DataSetPopulator<SDNGDataSet>
{
    private final ActionDoneParser actionDoneParser;
    private final ErrorParser errorParser;

    @Autowired
    public SDNGDataSetPopulator(ActionDoneParser actionDoneParser, ErrorParser errorParser) {
        this.actionDoneParser = actionDoneParser;
        this.errorParser = errorParser;
    }

    @Override
    public void populate(String line, SDNGDataSet dataSet)
    {
        actionDoneParser.parseLine(line, dataSet.getActionsDone());
        errorParser.parseLine(line, dataSet.getErrors());
    }
}
