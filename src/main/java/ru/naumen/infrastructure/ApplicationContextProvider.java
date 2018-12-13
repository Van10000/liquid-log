package ru.naumen.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider
{
    private static ApplicationContext context;

    @Autowired
    public ApplicationContextProvider(ApplicationContext appContext)
    {
        context = appContext;
    }

    public static ApplicationContext getContext()
    {
        return context;
    }
}
