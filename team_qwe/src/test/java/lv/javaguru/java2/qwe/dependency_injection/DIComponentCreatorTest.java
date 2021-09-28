package lv.javaguru.java2.qwe.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DIComponentCreatorTest {

    @Test
    public void test() throws IOException {
        ClassFinder classFinder = new ClassFinder();
        DIComponentFilter diComponentFilter = new DIComponentFilter();
        ApplicationContext context = new ApplicationContext();
        DIComponentCreator diComponentCreator = new DIComponentCreator();
        List<Class<?>> allClasses = classFinder.findClassesInsidePackage();
        List<Class<?>> diComponentClasses = diComponentFilter.filter(allClasses);
        diComponentCreator.create(context, diComponentClasses);
        context.getBeans().forEach((key, value) -> System.out.println(key.getName()));
//        assertEquals(1, context.getBeans().size());
    }

}