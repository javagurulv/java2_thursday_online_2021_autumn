package lv.javaguru.java2.qwe.dependency_injection;

import lv.javaguru.java2.qwe.core.database.Database;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DIComponentCreator {

    private final List<Class<?>> list = List.of(
            Database.class
    );

    public DIComponentCreator() {
    }

    public void create(ApplicationContext applicationContext,
                       List<Class<?>> diComponents) {
        diComponents.forEach(diComponent -> {
            Optional<Constructor<?>> defaultConstructorOpt = getDefaultConstructor(diComponent);
            if (defaultConstructorOpt.isPresent()) {
                Object diComponentInstance = createInstanceUsingDefaultConstructor(defaultConstructorOpt.get());
                applicationContext.addBean(diComponent, diComponentInstance);
            } else {
                throw new RuntimeException("Class do not have default constructor!");
            }
        });
    }

    private Optional<Constructor<?>> getDefaultConstructor(Class<?> diComponent) {
        return Arrays.stream(diComponent.getConstructors())
                .filter(constructor -> constructor.getParameterCount() == 0 ||
                        classHasParameterizedConstructor(constructor))
                .findFirst();
    }

    private Object createInstanceUsingDefaultConstructor(Constructor<?> defaultConstructor) {
        try {
            return (defaultConstructor.getParameterCount() == 0) ? defaultConstructor.newInstance() :
                    defaultConstructor.newInstance("TEST!", 123);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean classHasParameterizedConstructor(Constructor<?> constructor) {
        return list.contains(constructor.getDeclaringClass().getInterfaces()[0]);
    }

}