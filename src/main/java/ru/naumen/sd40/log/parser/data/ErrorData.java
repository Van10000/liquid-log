package ru.naumen.sd40.log.parser.data;

public class ErrorData
{
    private long warnCount = 0;
    private long errorCount = 0;
    private long fatalCount = 0;

    public long getWarnCount()
    {
        return warnCount;
    }

    public void increaseWarnCount(int value)
    {
        warnCount += value;
    }

    public long getErrorCount()
    {
        return errorCount;
    }

    public void increaseErrorCount(int value)
    {
        errorCount += value;
    }

    public long getFatalCount()
    {
        return fatalCount;
    }

    public void increaseFatalCount(int value)
    {
        fatalCount += value;
    }
}
