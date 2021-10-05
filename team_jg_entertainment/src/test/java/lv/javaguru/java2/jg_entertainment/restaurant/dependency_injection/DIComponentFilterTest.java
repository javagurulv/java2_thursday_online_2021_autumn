package lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class DIComponentFilterTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        ClassFinder classFinder = new ClassFinder();
        DIComponentFilter filter = new DIComponentFilter();
        List<Class> classes = classFinder.findClassesInsidePackage("lv.javaguru.java2.jg_entertainment.restaurant");
        List<Class> diComponent = filter.filter(classes);
        diComponent.forEach(aClass -> System.out.println(aClass.getName()));
    }

}