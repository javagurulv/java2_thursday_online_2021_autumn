package lv.javaguru.java2.qwe.dependency_injection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClassFinder {

    public List<Class<?>> findClassesInsidePackage(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

/*    public List<Class<?>> findClassesInsidePackage() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = "lv/javaguru/java2/qwe";
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        File[] files = dirs.stream()
                .map(File::listFiles)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .toArray(File[]::new);
        List<File> fileList = IntStream.rangeClosed(0, files.length - 1)
                .mapToObj(i -> files[i])
                .flatMap(file1 -> getFiles(file1).stream())
                .collect(Collectors.toList());
        return fileList.stream()
                .map(ClassFinder::getClass)
                .collect(Collectors.toList());
    }

    private List<File> getFiles(File file) {
        List<File> list = new ArrayList<>();
        if (file.isFile()) {
            list.add(file);
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            IntStream.rangeClosed(0, files.length - 1)
                    .forEach(i -> {
                        List<File> list1 = getFiles(files[i]);
                        list.addAll(list1);
                    });
        }
        return list;
    }

    private static Class<?> getClass(File file) {
        String path1 = file.getPath().substring(0, file.getPath().length() - file.getName().length());
        String path2 = path1.substring(file.getPath().indexOf("lv\\javaguru"));
        String path3 = path2.replace("\\", ".");
        try {
            return Class.forName(path3 + file.getName().substring(0, file.getName().length() - 6));
        } catch (ClassNotFoundException e) {
            return RuntimeException.class;
        }
    }*/

}