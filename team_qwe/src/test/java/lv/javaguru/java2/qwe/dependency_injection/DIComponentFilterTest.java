package lv.javaguru.java2.qwe.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DIComponentFilterTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        ClassFinder classFinder = new ClassFinder();
        DIComponentFilter diComponentFilter = new DIComponentFilter();
        List<Class<?>> allClasses = classFinder.findClassesInsidePackage("lv.javaguru.java2.qwe");
        List<Class<?>> diComponentClasses = diComponentFilter.filter(allClasses);
        diComponentClasses.forEach(aClass -> System.out.println(aClass.getName()));
    }

}