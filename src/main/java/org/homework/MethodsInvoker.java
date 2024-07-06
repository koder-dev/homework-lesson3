package org.homework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodsInvoker {

    public static List<Object> invoke(Object obj) throws InvocationTargetException, IllegalAccessException {
        Class<?> cls = obj.getClass();
        List<Object> list = new ArrayList<>();
        for (Method method1 : cls.getDeclaredMethods()) {
            if (method1.isAnnotationPresent(MethodParams.class)) {
                MethodParams params = method1.getAnnotation(MethodParams.class);
                Object apply = method1.invoke(null, params.param1(), params.param2());
                list.add(apply);
            }
        }
        return list;
    }

    public static void invokeSaveMethod(TextContainer container) throws IllegalAccessException, InvocationTargetException {
        Class<?> textContainerClass = container.getClass();
        if (textContainerClass.isAnnotationPresent(SaveTo.class)) {
            SaveTo annotation = textContainerClass.getAnnotation(SaveTo.class);
            String path = annotation.path();
            String methodName = annotation.methodName();
            Class<Saver> saverClass = Saver.class;
            for (Method method : saverClass.getDeclaredMethods()) {
                if (method.getName().equals(methodName)) {
                    method.invoke(null, path, container.getText());
                }
            }
        }
    }
}
