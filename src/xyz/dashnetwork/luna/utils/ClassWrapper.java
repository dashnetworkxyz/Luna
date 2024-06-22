package xyz.dashnetwork.luna.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class ClassWrapper {

    private Class<?> clazz;
    private Object instance;

    public ClassWrapper(String clazzNameFirst, String clazzNameLast) {
        try {
            this.clazz = Class.forName(clazzNameFirst + "." + PlatformUtils.getNMSPackage() + "." + clazzNameLast);
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException(exception);
        }

        instance = null;
    }

    public ClassWrapper(Class<?> clazz) {
        this.clazz = clazz;
        this.instance = null;
    }

    public ClassWrapper(Object instance) {
        this.clazz = instance.getClass();
        this.instance = instance;
    }

    public <T>T callMethod(String methodName, Object... parameters) {
        return callMethod(methodName, false, parameters);
    }

    public <T>T callMethodDeclared(String methodName, Object... parameters) {
        return callMethod(methodName, true, parameters);
    }

    private <T>T callMethod(String methodName, boolean declared, Object...parameters) {
        try {
            List<Class<?>> classList = new ArrayList<>();

            for (Object parameter : parameters)
                classList.add(remapClass(parameter.getClass()));

            Method method;

            if (declared)
                method = clazz.getDeclaredMethod(methodName, classList.toArray(Class<?>[]::new));
            else
                method = clazz.getMethod(methodName, classList.toArray(Class<?>[]::new));

            method.setAccessible(true);

            return (T) method.invoke(instance, parameters);
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException(exception);
        }
    }

    public <T>T getField(String fieldName) {
        return getField(fieldName, false);
    }

    public <T>T getFieldDeclared(String fieldName) {
        return getField(fieldName, true);
    }

    private <T>T getField(String fieldName, boolean declared) {
        try {
            Field field;

            if (declared)
                field = clazz.getDeclaredField(fieldName);
            else
                field = clazz.getField(fieldName);

            field.setAccessible(true);

            return (T) field.get(instance);
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Class<?> remapClass(Class<?> clazz) {
        if (clazz.equals(Boolean.class))
            return boolean.class;

        if (clazz.equals(Byte.class))
            return byte.class;

        if (clazz.equals(Short.class))
            return short.class;

        if (clazz.equals(Integer.class))
            return int.class;

        if (clazz.equals(Long.class))
            return long.class;

        if (clazz.equals(Float.class))
            return float.class;

        if (clazz.equals(Double.class))
            return double.class;

        return clazz;
    }

}
