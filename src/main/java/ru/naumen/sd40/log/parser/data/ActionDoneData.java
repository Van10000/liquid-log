package ru.naumen.sd40.log.parser.data;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
import java.util.HashMap;

public class ActionDoneData
{
    private ArrayList<Integer> times = new ArrayList<>();
    private double min;
    private double mean;
    private double stddev;
    private double percent50;
    private double percent95;
    private double percent99;

    private double percent999;
    private double max;
    private long count;
    private int addObjectActions = 0;
    private int editObjectsActions = 0;
    private int getListActions = 0;
    private int commentActions = 0;

    private int getFormActions = 0;

    private int getDtObjectActions = 0;

    private int searchActions = 0;

    private int getCatalogsActions = 0;

    private boolean nan = true;

    private HashMap<String, Integer> actions = new HashMap<>();

    public int geListActions()
    {
        return getListActions;
    }

    public void increaseListActions(int value)
    {
        getListActions += value;
    }

    public HashMap<String, Integer> getActionsCounter()
    {
        return actions;
    }

    public int getAddObjectActions()
    {
        return addObjectActions;
    }

    public void increaseAddObjectActions(int value)
    {
        addObjectActions += value;
    }

    public int getCommentActions()
    {
        return commentActions;
    }

    public void increaseCommentActions(int value)
    {
        commentActions += value;
    }

    public long getCount()
    {
        return count;
    }

    public int getDtObjectActions()
    {
        return getDtObjectActions;
    }

    public void increaseDtObjectActions(int value)
    {
        getDtObjectActions += value;
    }

    public int getEditObjectsActions()
    {
        return editObjectsActions;
    }
    
    public void increaseEditObjectsActions(int value) {
        editObjectsActions += value;
    }

    public int getFormActions()
    {
        return getFormActions;
    }

    public void increaseFormActions(int value)
    {
        getFormActions += value;
    }

    public double getMax()
    {
        return max;
    }

    public double getMean()
    {
        return mean;
    }

    public double getMin()
    {
        return min;
    }

    public double getPercent50()
    {
        return percent50;
    }

    public double getPercent95()
    {
        return percent95;
    }

    public double getPercent99()
    {
        return percent99;
    }

    public double getPercent999()
    {
        return percent999;
    }

    public int getSearchActions()
    {
        return searchActions;
    }

    public void increaseSearchActions(int value)
    {
        searchActions += value;
    }

    public double getStddev()
    {
        return stddev;
    }

    public ArrayList<Integer> getTimes()
    {
        return times;
    }

    public boolean isNan()
    {
        return nan;
    }

    public int getCatalogsActions()
    {
        return getCatalogsActions;
    }

    public void increaseCatalogsActions(int value)
    {
        getCatalogsActions += value;
    }

    public void calculate()
    {
        DescriptiveStatistics ds = new DescriptiveStatistics();
        times.forEach(ds::addValue);
        min = ds.getMin();
        mean = ds.getMean();
        stddev = ds.getStandardDeviation();
        percent50 = ds.getPercentile(50.0);
        percent95 = ds.getPercentile(95.0);
        percent99 = ds.getPercentile(99.0);
        percent999 = ds.getPercentile(99.9);
        max = ds.getMax();
        count = ds.getN();
        nan = count == 0;
    }
}
