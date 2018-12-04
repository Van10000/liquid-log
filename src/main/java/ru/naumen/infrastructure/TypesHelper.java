package ru.naumen.infrastructure;

import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;

public class TypesHelper
{
    private TypesHelper() {}

    public static ResolvableType getGeneric(Class base, Class genericType)
    {
        return ResolvableType.forClassWithGenerics(base, genericType);
    }

    public static Object findGeneric(ApplicationContext context, Class base, Class genericType)
    {
        return context.getBean(context.getBeanNamesForType(getGeneric(base, genericType))[0]);
    }

    public static Object[] findGenerics(ApplicationContext context, Class[] bases, Class genericType)
    {
        Object[] generics = new Object[bases.length];
        for (int i = 0; i < bases.length; ++i)
            generics[i] = TypesHelper.findGeneric(context, bases[i], genericType);
         return generics;
    }
}
