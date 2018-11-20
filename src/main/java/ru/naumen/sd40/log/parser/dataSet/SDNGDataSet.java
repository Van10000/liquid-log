package ru.naumen.sd40.log.parser.dataSet;

import ru.naumen.sd40.log.parser.data.ActionDoneData;
import ru.naumen.sd40.log.parser.data.ErrorData;

public class SDNGDataSet implements DataSet
{
    private ActionDoneData actionsDone;
    private ErrorData errors;

    public ActionDoneData getActionsDone()
    {
        return actionsDone;
    }

    public ErrorData getErrors()
    {
        return errors;
    }
}
