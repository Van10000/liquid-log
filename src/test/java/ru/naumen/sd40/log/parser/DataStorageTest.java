package ru.naumen.sd40.log.parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.naumen.DBConnector;
import ru.naumen.sd40.log.parser.dataParser.DataSet;

import static org.mockito.Mockito.*;

public class DataStorageTest
{
    private DBConnector dbConnector;
    private DataStorage storage;

    @Before
    public void setUp()
    {
        dbConnector = Mockito.mock(DBConnector.class);
        storage = new DataStorage(dbConnector);
    }

    @Test
    public void mustUploadWhenKeyIncreased() throws DataStorage.AlreadyProcessedKeyException
    {
        //when
        storage.get(0);
        storage.get(1);

        //then
        verify(dbConnector).store(eq(0L), any(DataSet.class));
    }

    @Test
    public void mustUploadWhenClosed() throws DataStorage.AlreadyProcessedKeyException
    {
        //when
        storage.get(0);
        storage.close();

        //then
        verify(dbConnector).store(eq(0L), any(DataSet.class));
    }

    @Test(expected = DataStorage.AlreadyProcessedKeyException.class)
    public void mustThrowIfIncorrectOrder() throws DataStorage.AlreadyProcessedKeyException {
        //when
        storage.get(1);
        storage.get(0);
    }
}