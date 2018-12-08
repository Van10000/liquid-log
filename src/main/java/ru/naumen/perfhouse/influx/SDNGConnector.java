package ru.naumen.perfhouse.influx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.data.ActionDoneData;
import ru.naumen.sd40.log.parser.data.ErrorData;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;

@Component
@Scope("request")
public class SDNGConnector extends BaseConnector<SDNGDataSet>
{
    @Autowired
    public SDNGConnector(InfluxDAO storage)
    {
        super(storage);
    }

    @Override
    public void store(long key, SDNGDataSet dataSet)
    {
        ActionDoneData dones = dataSet.getActionsDone();
        dones.calculate();
        ErrorData errors = dataSet.getErrors();
        if (traceResult)
        {
            System.out.print(String.format("%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%d\n", key, dones.getCount(),
                    dones.getMin(), dones.getMean(), dones.getStddev(), dones.getPercent50(), dones.getPercent95(),
                    dones.getPercent99(), dones.getPercent999(), dones.getMax(), errors.getErrorCount()));
        }
        if (!dones.isNan())
        {
            storage.storeActionsFromLog(dbName, key, dones, errors);
        }

    }
}
