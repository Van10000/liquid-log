package ru.naumen.sd40.log.parser.dataSet;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.data.ActionDoneData;
import ru.naumen.sd40.log.parser.data.ErrorData;

@Component("sdng")
public class SDNGDataSet implements DataSet
{
    private ActionDoneData actionsDone;
    private ErrorData errors;

    public SDNGDataSet()
    {
        actionsDone = new ActionDoneData();
        errors = new ErrorData();
    }

    public ActionDoneData getActionsDone()
    {
        return actionsDone;
    }

    public ErrorData getErrors()
    {
        return errors;
    }
}
