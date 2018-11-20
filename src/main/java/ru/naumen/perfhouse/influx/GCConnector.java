package ru.naumen.perfhouse.influx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.data.GCData;
import ru.naumen.sd40.log.parser.dataSet.GCDataSet;

@Component
public class GCConnector extends BaseConnector<GCDataSet>
{
    @Autowired
    public GCConnector(InfluxDAO storage)
    {
        super(storage);
    }

    @Override
    public void store(long key, GCDataSet dataSet)
    {
        GCData gc = dataSet.getGc();
        if (traceResult)
        {
            System.out.print(String.format("%f;%d;%f\n", gc.getCalculatedAvg(), gc.getGcTimes(), gc.getMaxGcTime()));
        }
        if (!gc.isNan())
        {
            storage.storeGc(dbName, key, gc);
        }
    }
}