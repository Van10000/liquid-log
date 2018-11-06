package ru.naumen.perfhouse.influx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.data.ActionDoneData;
import ru.naumen.sd40.log.parser.data.ErrorData;
import ru.naumen.sd40.log.parser.data.GCData;
import ru.naumen.sd40.log.parser.data.TopData;
import ru.naumen.sd40.log.parser.dataParser.*;

@Configurable
public class InfluxConnector implements DBConnector
{
    @Autowired
    private InfluxDAO storage;
    private String dbName;
    private boolean traceResult;

    public InfluxConnector(String dbName, boolean traceResult)
    {
        storage.init();
        storage.connectToDB(dbName);
        this.dbName = dbName;
        this.traceResult = traceResult;
    }

    @Override
    public void store(long key, DataSet dataSet)
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
            storage.storeActionsFromLog(null, dbName, key, dones, errors);
        }

        GCData gc = dataSet.getGc();
        if (!gc.isNan())
        {
            storage.storeGc(null, dbName, key, gc);
        }

        TopData topData = dataSet.getTop();
        if (!topData.isNan())
        {
            storage.storeTop(null, dbName, key, topData);
        }
    }
}
