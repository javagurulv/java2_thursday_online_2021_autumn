package lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection;

import java.util.List;
import java.util.stream.Collectors;

public class DIComponentFilter {

    public List<Class> filter(List<Class> classes) {
        return classes.stream()
                .filter(cl -> cl.isAnnotationPresent(DIComponent.class))
                .collect(Collectors.toList());
    }
}
