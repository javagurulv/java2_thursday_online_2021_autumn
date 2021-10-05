package lv.javaguru.java2.hospital.dependency_injection;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

class ClassFinderTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        List<Class> classes = finder.findClassesInsidePackage("lv.javaguru.java2.hospital");
        classes.forEach(aClass -> {
            System.out.println(aClass.getName());
        });
    }

}