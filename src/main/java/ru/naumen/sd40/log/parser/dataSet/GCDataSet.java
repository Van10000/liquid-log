package ru.naumen.sd40.log.parser.dataSet;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.data.GCData;

@Component("gc")
public class GCDataSet implements DataSet
{
    private GCData gc;

    public GCDataSet()
    {
        gc = new GCData();
    }

    public GCData getGc()
    {
        return gc;
    }
}
