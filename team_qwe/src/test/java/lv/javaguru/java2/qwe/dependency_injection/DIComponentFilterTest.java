package lv.javaguru.java2.qwe.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DIComponentFilterTest {

    @Test
    public void test() throws IOException {
        ClassFinder classFinder = new ClassFinder();
        DIComponentFilter diComponentFilter = new DIComponentFilter();
        List<Class<?>> allClasses = classFinder.findClassesInsidePackage();
        List<Class<?>> diComponentClasses = diComponentFilter.filter(allClasses);
        diComponentClasses.forEach(aClass -> System.out.println(aClass.getName()));
    }

}