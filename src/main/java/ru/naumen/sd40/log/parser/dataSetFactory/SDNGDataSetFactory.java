package ru.naumen.sd40.log.parser.dataSetFactory;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;

@Component
public class SDNGDataSetFactory implements DataSetFactory<SDNGDataSet>
{
    @Override
    public SDNGDataSet create()
    {
        return new SDNGDataSet();
    }
}
