package ru.naumen.perfhouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.naumen.sd40.log.parser.DataStorage;
import ru.naumen.perfhouse.influx.InfluxConnector;
import ru.naumen.sd40.log.parser.LogParser;

@Controller
public class ParseController
{
    @RequestMapping(path = "/parse", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void parse(@RequestParam("DBName") String dbName,
                      @RequestParam("LogPath") String logPath,
                      @RequestParam("ParseMode") String parseMode,
                      @RequestParam(name="Timezone", required=false) String timezone,
                      @RequestParam(name="TraceResult", required=false) String traceResult)
    {
        DataStorage storage = new DataStorage(new InfluxConnector(
                dbName,
                System.getProperty("influx.host"),
                System.getProperty("influx.user"),
                System.getProperty("influx.password"),
                traceResult.equals("true")));
        try {
            LogParser.parseAndUpload(logPath, timezone, parseMode, storage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
