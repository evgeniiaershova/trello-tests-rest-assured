package http.refactored.entities;

import http.refactored.annotations.*;

import java.lang.reflect.Field;
import java.util.List;

import static com.epam.commons.LinqUtils.where;
import static http.refactored.ExceptionHandler.exception;
import static java.lang.reflect.Modifier.isStatic;

public class ServiceInit {
    public static <T> T init(Class<T> c) {
        List<Field> methods = where(c.getDeclaredFields(),
                f -> f.getType().equals(RestMethod.class));
        for (Field method: methods) {
            try {
                method.setAccessible(true);
                if (isStatic(method.getModifiers()))
                    method.set(null, getRestMethod(method, c));
                if (!isStatic(method.getModifiers()) && method.get(getService(c)) == null)
                    method.set(getService(c), getRestMethod(method, c));
            } catch (IllegalAccessException ex) {
                throw exception("Can't init method %s for class %s", method.getName(), c.getName()); }
        }
        return getService(c);
    }
    private static Object service;
    private static <T> T getService(Class<T> c) {
        if (service != null) return (T) service;
        try {
            return (T) (service = c.newInstance());
        } catch (IllegalAccessException|InstantiationException ex) {
            throw exception(
                "Can't instantiate class %s, Service class should have empty constructor",
                    c.getSimpleName()); }
    }
    private static <T> RestMethod getRestMethod(Field field, Class<T> c) {
        MethodData mtData = getMethodData(field);
        RestMethod method = new RestMethod(getDomain(c), mtData.getResourceUrl());
        if (field.isAnnotationPresent(ContentType.class))
            method.setContentType(field.getAnnotation(ContentType.class).value());
        if (field.isAnnotationPresent(Header.class))
            method.addHeader(field.getAnnotation(Header.class));
        if (field.isAnnotationPresent(Headers.class))
            method.addHeaders(field.getAnnotation(Headers.class).value());
        /* Case for class annotations*/
        if (c.isAnnotationPresent(QueryParameter.class))
             method.addQueryParameters(c.getAnnotation(QueryParameter.class));
        if (c.isAnnotationPresent(QueryParameters.class))
            method.addQueryParameters(c.getAnnotation(QueryParameters.class).value());
//         Case for method annotations
        if (field.isAnnotationPresent(QueryParameter.class))
            method.addQueryParameters(field.getAnnotation(QueryParameter.class));
        if (field.isAnnotationPresent(QueryParameters.class))
            method.addQueryParameters(field.getAnnotation(QueryParameters.class).value());
        return method;
    }

    private static MethodData getMethodData(Field method) {
        if (method.isAnnotationPresent(Resource.class)){
            return new MethodData(method.getAnnotation(Resource.class).value());
        }
        return new MethodData("");
    }

    private static <T> String getDomain(Class<T> c) {
        return c.isAnnotationPresent(ServiceDomain.class)
                ? c.getAnnotation(ServiceDomain.class).value()
                : null;
    }
}
