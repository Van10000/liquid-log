package ru.naumen.perfhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.naumen.sd40.log.parser.DataStorage;
import ru.naumen.perfhouse.influx.InfluxConnector;
import ru.naumen.sd40.log.parser.LogParser;

@Controller
public class ParseController
{
    private final LogParser parser;

    @Autowired
    public ParseController(LogParser parser) {
        this.parser = parser;
    }

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
                traceResult.equals("true")));
        try {
            parser.parseAndUpload(logPath, timezone, parseMode, storage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
