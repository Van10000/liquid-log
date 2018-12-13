package ru.naumen.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataSet.DataSet;

import java.util.HashMap;

@Component
public class ParseModeLocator
{
    public final ApplicationContext context;

    @Autowired
    public ParseModeLocator(ApplicationContext context)
    {
        this.context = context;
    }

    public String[] getParseModes()
    {
        return context.getBeanNamesForType(DataSet.class);
    }

    public HashMap<String, Class> getDataSetTypes()
    {
        HashMap<String, Class> dataSetTypes = new HashMap<>();
        String[] parseModes = getParseModes();
        for (String parseMode : parseModes)
            dataSetTypes.put(parseMode, context.getBean(parseMode).getClass());
        return dataSetTypes;
    }
}
