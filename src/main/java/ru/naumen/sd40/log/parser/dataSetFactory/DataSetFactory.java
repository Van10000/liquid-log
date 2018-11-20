package ru.naumen.sd40.log.parser.dataSetFactory;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataSet.DataSet;

@Component
public interface DataSetFactory<TDataSet extends DataSet>
{
    TDataSet create();
}
