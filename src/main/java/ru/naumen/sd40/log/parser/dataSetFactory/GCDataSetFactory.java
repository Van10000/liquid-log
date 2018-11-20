package ru.naumen.sd40.log.parser.dataSetFactory;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataSet.GCDataSet;

@Component
public class GCDataSetFactory implements DataSetFactory<GCDataSet>
{
    @Override
    public GCDataSet create()
    {
        return new GCDataSet();
    }
}
