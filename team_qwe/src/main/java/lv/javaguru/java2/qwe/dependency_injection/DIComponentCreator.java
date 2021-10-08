package lv.javaguru.java2.qwe.dependency_injection;

import lv.javaguru.java2.qwe.core.database.DatabaseImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.*;

public class DIComponentCreator {

    private final Map<Class<?>, Object> list = ofEntries(
            entry(DatabaseImpl.class, new DatabaseImpl())
    );

    public void create(ApplicationContext applicationContext,
                       List<Class<?>> diComponents) {
        diComponents.forEach(diComponent -> {
            Optional<Constructor<?>> constructorOpt = getConstructor(diComponent);
            if (constructorOpt.isPresent()) {
                Object diComponentInstance = createInstanceUsingConstructor(constructorOpt.get());
                applicationContext.addBean(diComponent, diComponentInstance);
            } else {
                throw new RuntimeException("Class do not have default constructor!");
            }
        });
    }

    private Optional<Constructor<?>> getConstructor(Class<?> diComponent) {
        return Arrays.stream(diComponent.getConstructors())
                .filter(constructor -> constructor.getParameterCount() == 0 ||
                        classHasParameterizedConstructor(constructor))
                .findFirst();
    }

    private Object createInstanceUsingConstructor(Constructor<?> constructor) {
        try {
            return (constructor.getParameterCount() == 0) ? constructor.newInstance() :
                    list.get(constructor.getDeclaringClass());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean classHasParameterizedConstructor(Constructor<?> constructor) {
        return list.containsKey(constructor.getDeclaringClass());
    }

}