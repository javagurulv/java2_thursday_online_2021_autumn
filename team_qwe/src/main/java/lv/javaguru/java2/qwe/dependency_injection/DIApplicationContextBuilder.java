package lv.javaguru.java2.qwe.dependency_injection;

import java.util.List;

public class DIApplicationContextBuilder {

    private final ClassFinder classFinder = new ClassFinder();
    private final DIComponentFilter componentFilter = new DIComponentFilter();
    private final DIComponentCreator componentsCreator = new DIComponentCreator();
    private final DIDependencyResolver dependencyResolver = new DIDependencyResolver();

    public ApplicationContext build(String packageName) {
        try {
            List<Class<?>> allPackageClasses = classFinder.findClassesInsidePackage(packageName);
            List<Class<?>> diComponents = componentFilter.filter(allPackageClasses);

            ApplicationContext applicationContext = new ApplicationContext();
            componentsCreator.create(applicationContext, diComponents);
            dependencyResolver.resolve(applicationContext, diComponents);

            return applicationContext;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}