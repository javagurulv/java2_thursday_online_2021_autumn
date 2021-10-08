package lv.javaguru.java2.qwe.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DIComponentCreatorTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        ClassFinder classFinder = new ClassFinder();
        DIComponentFilter diComponentFilter = new DIComponentFilter();
        ApplicationContext context = new ApplicationContext();
        DIComponentCreator diComponentCreator = new DIComponentCreator();
        List<Class<?>> allClasses = classFinder.findClassesInsidePackage("lv.javaguru.java2.qwe");
        List<Class<?>> diComponentClasses = diComponentFilter.filter(allClasses);
        diComponentCreator.create(context, diComponentClasses);
        context.getBeans().forEach((key, value) -> System.out.println(key.getName()));
    }

}