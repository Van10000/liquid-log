package ru.naumen.sd40.log.parser.dataParser;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.data.TopData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TopParser
{
    private Pattern cpuAndMemPattren = Pattern
        .compile("^ *\\d+ \\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ \\S+ +(\\S+) +(\\S+) +\\S+ java");
    private Pattern loadAverage = Pattern.compile(".*load average:(.*)");

    public void parseLine(String line, TopData data)
    {
        //get la
        Matcher la = loadAverage.matcher(line);
        if (la.find())
        {
            data.addLa(Double.parseDouble(la.group(1).split(",")[0].trim()));
            return;
        }

        //get cpu and mem
        Matcher cpuAndMemMatcher = cpuAndMemPattren.matcher(line);
        if (cpuAndMemMatcher.find())
        {
            data.addCpu(Double.valueOf(cpuAndMemMatcher.group(1)));
            data.addMem(Double.valueOf(cpuAndMemMatcher.group(2)));
        }
    }
}
