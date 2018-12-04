package ru.naumen.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import ru.naumen.DBConnector;
import ru.naumen.infrastructure.TypesHelper;
import ru.naumen.sd40.log.parser.dataSet.DataSet;
import ru.naumen.sd40.log.parser.dataSet.GCDataSet;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;
import ru.naumen.sd40.log.parser.dataSet.TopDataSet;
import ru.naumen.sd40.log.parser.dataSetFactory.DataSetFactory;
import ru.naumen.sd40.log.parser.dataStorage.DataStorage;

@Lazy
@Configuration
public class DataStorageConfiguration
{
    private final ApplicationContext appContext;

    @Autowired
    public DataStorageConfiguration(ApplicationContext appContext)
    {
        this.appContext = appContext;
    }

    public <TDataSet extends DataSet> DataStorage<TDataSet> getParseRunner(Class dataSetClass)
    {
        Class[] baseClasses = {DBConnector.class, DataSetFactory.class};
        Object[] dependencies = TypesHelper.findGenerics(appContext, baseClasses, dataSetClass);
        return (DataStorage<TDataSet>) appContext.getBean("dataStorage", dependencies);
    }

    @Bean("sdngStorage")
    public DataStorage<SDNGDataSet> createSDNGStorage()
    {
        return getParseRunner(SDNGDataSet.class);
    }

    @Bean("topStorage")
    public DataStorage<TopDataSet> createTopStorage()
    {
        return getParseRunner(TopDataSet.class);
    }

    @Bean("gcStorage")
    public DataStorage<GCDataSet> createGCStorage()
    {
        return getParseRunner(GCDataSet.class);
    }
}