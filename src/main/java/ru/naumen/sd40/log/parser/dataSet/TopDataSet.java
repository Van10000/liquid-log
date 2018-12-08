package ru.naumen.sd40.log.parser.dataSet;

import ru.naumen.sd40.log.parser.data.TopData;

public class TopDataSet implements DataSet
{
    private TopData top;

    public TopDataSet()
    {
        top = new TopData();
    }

    public TopData getTop()
    {
        return top;
    }
}