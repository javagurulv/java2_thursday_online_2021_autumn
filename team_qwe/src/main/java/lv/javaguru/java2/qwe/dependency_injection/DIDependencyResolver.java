package lv.javaguru.java2.qwe.dependency_injection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DIDependencyResolver {

/*    public void resolve(ApplicationContext applicationContext, List<Class<?>> diComponents) {
        diComponents.stream()
                .map(Class::getDeclaredFields)
                .map(fields -> Arrays.stream(fields)
                        .filter(field -> field.isAnnotationPresent(DIDependency.class))
                        .collect(Collectors.toList()))
                .forEach(list -> list
                        .forEach(field -> {
                            try {
                                field.setAccessible(true);
                                field.set(field.getClass(), applicationContext.getBean(field.getClass()));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }));
    }*/

    public void resolve(ApplicationContext applicationContext,
                        List<Class<?>> diComponents) {
        diComponents.forEach(diComponent -> {
            Object diInstance = applicationContext.getBean(diComponent);
            List<Field> diFields = findFieldsWithDIDependencyAnnotation(diComponent);
            diFields.forEach(field -> tryToSetDependency(applicationContext, diInstance, field));
        });
    }

    private void tryToSetDependency(ApplicationContext applicationContext, Object diInstance, Field field) {
        Class<?> fieldType = field.getType();
        Object fieldInstance = applicationContext.getBean(fieldType);
        if (fieldInstance == null) {
            throw new RuntimeException("No dependency found!");
        } else {
            setValueToPrivateField(diInstance, field, fieldInstance);
        }
    }

    private void setValueToPrivateField(Object diInstance, Field field, Object fieldInstance) {
        try {
            field.setAccessible(true);
            field.set(diInstance, fieldInstance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Can not resolve dependency!");
        }
    }

    private List<Field> findFieldsWithDIDependencyAnnotation(Class<?> diComponent) {
        return Arrays.stream(diComponent.getDeclaredFields())
                .filter(filed -> filed.isAnnotationPresent(DIDependency.class))
                .collect(Collectors.toList());
    }

}