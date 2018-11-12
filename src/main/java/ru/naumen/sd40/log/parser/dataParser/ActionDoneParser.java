package ru.naumen.sd40.log.parser.dataParser;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.data.ActionDoneData;

/**
 * Created by doki on 22.10.16.
 */
@Component
public class ActionDoneParser
{
    private static Set<String> EXCLUDED_ACTIONS = new HashSet<>();

    static
    {
        EXCLUDED_ACTIONS.add("EventAction".toLowerCase());
    }

    Pattern doneRegEx = Pattern.compile("Done\\((\\d+)\\): ?(.*?Action)");

    public void parseLine(String line, ActionDoneData data)
    {
        Matcher matcher = doneRegEx.matcher(line);

        if (matcher.find())
        {
            String actionInLowerCase = matcher.group(2).toLowerCase();
            if (EXCLUDED_ACTIONS.contains(actionInLowerCase))
            {
                return;
            }

            data.getTimes().add(Integer.parseInt(matcher.group(1)));
            if (actionInLowerCase.equals("addobjectaction"))
            {
                data.increaseAddObjectActions(1);
            }
            else if (actionInLowerCase.equals("editobjectaction"))
            {
                data.increaseEditObjectsActions(1);
            }
            else if (actionInLowerCase.equals("getcatalogsaction"))
            {
                data.increaseCatalogsActions(1);
            }
            else if (actionInLowerCase.matches("(?i)[a-zA-Z]+comment[a-zA-Z]+"))
            {
                data.increaseCommentActions(1);
            }
            else if (!actionInLowerCase.contains("advlist")
                    && actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+List[a-zA-Z]+"))

            {
                data.increaseListActions(1);
            }
            else if (actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+Form[a-zA-Z]+"))
            {
                data.increaseFormActions(1);
            }
            else if (actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+DtObject[a-zA-Z]+"))
            {
                data.increaseDtObjectActions(1);
            }
            else if (actionInLowerCase.matches("(?i)[a-zA-Z]+search[a-zA-Z]+"))
            {
                data.increaseSearchActions(1);
            }

        }
    }
}
