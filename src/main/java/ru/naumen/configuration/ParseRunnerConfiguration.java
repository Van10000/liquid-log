package ru.naumen.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import ru.naumen.DBConnector;
import ru.naumen.infrastructure.TypesHelper;
import ru.naumen.perfhouse.controllers.ParseController;
import ru.naumen.sd40.log.parseRunner.ParseRunner;
import ru.naumen.sd40.log.parser.LogParser;
import ru.naumen.sd40.log.parser.dataSet.DataSet;
import ru.naumen.sd40.log.parser.dataSet.GCDataSet;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;
import ru.naumen.sd40.log.parser.dataSet.TopDataSet;

@Lazy
@Configuration
public class ParseRunnerConfiguration
{
    private final ApplicationContext appContext;

    @Autowired
    public ParseRunnerConfiguration(ApplicationContext appContext)
    {
        this.appContext = appContext;
    }

    public <TDataSet extends DataSet> ParseRunner<TDataSet> getParseRunner(Class dataSetClass)
    {
        Object baseConnector = TypesHelper.findGeneric(appContext, DBConnector.class, dataSetClass);
        String[] classParts = dataSetClass.getName().split("\\.");
        String namePart = classParts[classParts.length - 1];
        String parseMode = namePart.substring(0, namePart.indexOf("DataSet")).toLowerCase();

        Object[] dependencies = {
                appContext.getBean(parseMode + "Storage"),
                appContext.getBean(parseMode + "LogParser"),
                baseConnector,
                appContext.getAutowireCapableBeanFactory()
        };

        return (ParseRunner<TDataSet>) appContext.getBean("parseRunner", dependencies);
    }

    @Bean("sdngParseRunner")
    public ParseRunner<SDNGDataSet> createSDNGRunner()
    {
        return getParseRunner(SDNGDataSet.class);
    }

    @Bean("topParseRunner")
    public ParseRunner<TopDataSet> createTopRunner()
    {
        return getParseRunner(TopDataSet.class);
    }

    @Bean("gcParseRunner")
    public ParseRunner<GCDataSet> createGCRunner()
    {
        return getParseRunner(GCDataSet.class);
    }
}