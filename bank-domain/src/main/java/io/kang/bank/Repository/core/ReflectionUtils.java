package io.kang.bank.Repository.core;

import org.yaml.snakeyaml.events.Event;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {
    public static List<String> getKeys(Object obj) {
        List<String> keys = new ArrayList<>();

        if(obj != null) {
            Class<?> clz = obj.getClass();
            Field[] fileds = clz.getDeclaredFields();

            for(Field field: fileds) {
                keys.add(field.getName());
            }
        }

        return keys;
    }

    public static Object getFieldValue(Object obj, String fieldName) throws Exception {

        Class<?> clz = obj.getClass();

        Method method = clz.getMethod("get" + getMethodName(fieldName));
        Object value = method.invoke(obj);

        return value;
    }

    public static void writeField(String fieldName, Object obj, Object fieldValue) {
        Class<?> clz = obj.getClass();
        try {
            Field field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getMethodName(String fieldName) {
        byte[] items = fieldName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}