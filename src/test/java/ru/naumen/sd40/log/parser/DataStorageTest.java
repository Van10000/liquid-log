package ru.naumen.sd40.log.parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;
import ru.naumen.sd40.log.parser.dataSetFactory.SDNGDataSetFactory;
import ru.naumen.sd40.log.parser.exceptions.AlreadyProcessedKeyException;

import static org.mockito.Mockito.*;

public class DataStorageTest
{
    private DBConnector<SDNGDataSet> dbConnector;
    private DataStorage<SDNGDataSet> storage;

    @Before
    public void setUp()
    {
        dbConnector = (DBConnector<SDNGDataSet>) Mockito.mock(DBConnector.class);
        storage = new DataStorage<>(dbConnector, new SDNGDataSetFactory());
    }

    @Test
    public void mustUploadWhenKeyIncreased() throws AlreadyProcessedKeyException
    {
        //when
        storage.get(0);
        storage.get(1);

        //then
        verify(dbConnector).store(eq(0L), any(SDNGDataSet.class));
    }

    @Test
    public void mustUploadWhenClosed() throws AlreadyProcessedKeyException
    {
        //when
        storage.get(0);
        storage.close();

        //then
        verify(dbConnector).store(eq(0L), any(SDNGDataSet.class));
    }

    @Test(expected = AlreadyProcessedKeyException.class)
    public void mustThrowIfIncorrectOrder() throws AlreadyProcessedKeyException
    {
        //when
        storage.get(1);
        storage.get(0);
    }
}