package ru.naumen.sd40.log.parser.dataSetFactory;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataSet.TopDataSet;

@Component
public class TopDataSetFactory implements DataSetFactory<TopDataSet>
{
    @Override
    public TopDataSet create()
    {
        return new TopDataSet();
    }
}
