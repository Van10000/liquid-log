package ru.naumen.perfhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.naumen.infrastructure.TypesHelper;
import ru.naumen.sd40.log.parseRunner.ParseRunner;
import ru.naumen.sd40.log.parser.LogFormatException;
import ru.naumen.sd40.log.parser.dataSet.GCDataSet;
import ru.naumen.sd40.log.parser.dataSet.SDNGDataSet;
import ru.naumen.sd40.log.parser.dataSet.TopDataSet;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

@Controller
public class ParseController
{
    private final ApplicationContext appContext;
    private final HashMap<String, Class> dataSetTypes = new HashMap<String, Class>(){
        {
            put("sdng", SDNGDataSet.class);
            put("top", TopDataSet.class);
            put("gc", GCDataSet.class);
        }
    };

    @Autowired
    public ParseController(ApplicationContext appContext)
    {
        this.appContext = appContext;
    }

    private ParseRunner<?> getParseRunner(String parseMode)
    {
        return (ParseRunner<?>)appContext.getBean(parseMode + "ParseRunner");
    }

    @RequestMapping(path = "/parse", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void parse(@RequestParam("DBName") String dbName,
                      @RequestParam("LogPath") String logPath,
                      @RequestParam("ParseMode") String parseMode,
                      @RequestParam(name="Timezone", required=false) String timezone,
                      @RequestParam(name="TraceResult", required=false) String traceResult)
    {
        ParseRunner<?> runner = getParseRunner(parseMode);
        try
        {
            runner.run(logPath, timezone, parseMode, dbName, traceResult.equals("true"));
        } catch (ParseException | LogFormatException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
