package ru.naumen.sd40.log.parser.dataParser;

import ru.naumen.sd40.log.parser.data.ActionDoneData;
import ru.naumen.sd40.log.parser.data.ErrorData;
import ru.naumen.sd40.log.parser.data.GCData;
import ru.naumen.sd40.log.parser.data.TopData;

/**
 * Created by doki on 22.10.16.
 */
public class DataSet
{
    private ActionDoneData actionsDone;
    private ErrorData errors;
    private GCData gc;
    private TopData top;

    public DataSet()
    {
        actionsDone = new ActionDoneData();
        errors = new ErrorData();
        gc = new GCData();
        top = new TopData();
    }

    public ActionDoneData getActionsDone()
    {
        return actionsDone;
    }

    public ErrorData getErrors()
    {
        return errors;
    }

    public GCData getGc()
    {
        return gc;
    }

    public TopData getTop()
    {
        return top;
    }
}
