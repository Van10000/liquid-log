package ru.naumen.perfhouse.influx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.data.TopData;
import ru.naumen.sd40.log.parser.dataSet.TopDataSet;

@Component
public class TopConnector extends BaseConnector<TopDataSet>
{
    @Autowired
    public TopConnector(InfluxDAO storage)
    {
        super(storage);
    }

    @Override
    public void store(long key, TopDataSet dataSet)
    {
        TopData topData = dataSet.getTop();
        if (traceResult)
        {
            System.out.print(String.format("%f;%f;%f;%f;%f;%f",
                    topData.getAvgCpuUsage(), topData.getAvgLa(), topData.getAvgMemUsage(),
                    topData.getMaxCpu(), topData.getMaxLa(), topData.getMaxMem()));
        }
        if (!topData.isNan())
        {
            storage.storeTop(dbName, key, topData);
        }
    }
}
