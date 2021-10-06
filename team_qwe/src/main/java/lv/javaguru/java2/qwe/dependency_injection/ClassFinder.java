package lv.javaguru.java2.qwe.dependency_injection;

import java.util.*;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class ClassFinder {

    public List<Class<?>> findClassesInsidePackage(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new ArrayList<>(reflections.getSubTypesOf(Object.class));
    }

}