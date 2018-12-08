package ru.naumen.sd40.log.parser.dataSetFactory;

import ru.naumen.sd40.log.parser.dataSet.DataSet;

public interface DataSetFactory<TDataSet extends DataSet>
{
    TDataSet create();
}
