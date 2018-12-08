package ru.naumen.configuration;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import ru.naumen.sd40.log.parser.LogParser;
import ru.naumen.sd40.log.parser.dataSet.DataSet;
import ru.naumen.sd40.log.parser.dataSet.GCDataSet;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;
import ru.naumen.sd40.log.parser.dataSet.TopDataSet;

@Lazy
@Configuration
public class LogParserConfiguration
{
    private final ApplicationContext appContext;

    @Autowired
    public LogParserConfiguration(ApplicationContext appContext)
    {
        this.appContext = appContext;
    }

    public <TDataSet extends DataSet> LogParser<TDataSet> getParseRunner(Class dataSetClass)
    {
        return (LogParser<TDataSet>) appContext.getBean("logParser");
    }

    @Bean("sdngLogParser")
    public LogParser<SDNGDataSet> createSDNGParser()
    {
        return getParseRunner(SDNGDataSet.class);
    }

    @Bean("topLogParser")
    public LogParser<TopDataSet> createTopParser()
    {
        return getParseRunner(TopDataSet.class);
    }

    @Bean("gcLogParser")
    public LogParser<GCDataSet> createGCParser()
    {
        return getParseRunner(GCDataSet.class);
    }
}