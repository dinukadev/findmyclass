package com.ezsolutionz.common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

import com.ezsolutionz.exception.ClassFinderException;

/**
 * A utility class used to retrieve class files and to load the default paths loaded by the class loader
 * 
 * @author darseculeratne
 */
public final class ClassFinderUtil {

    /**
     * This is a recursive method which will scan the directory path and all its sub-directories to search for class
     * files
     * 
     * @param path the path to start the search from
     * @param basePath this path is used in order to remove the initial folder path so that we will end up with the
     *            fully qualified class name
     * @param packagePath the package to search inside of
     * @param filePath the classes found will be added to this list
     */
    public static void retrieveFilePath(final String path, final String basePath, final String packagePath,
        final List<String> filePath) {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] content = file.list();

            for (String fileName : content) {
                File f;
                try {
                    f = new File(file.getCanonicalPath() + File.separator + fileName);

                    if (f.isDirectory()) {
                        retrieveFilePath(file.getCanonicalPath() + File.separator + fileName, basePath, packagePath,
                            filePath);
                    } else if (f.getCanonicalPath().contains(packagePath) && f.getName().contains(".class")) {
                        filePath.add(f.getCanonicalPath().replace(basePath, "").replace("\\", ".").replace("/", ".")
                            .replace(".class", "").substring(1));
                    }
                } catch (IOException e) {
                    throw new ClassFinderException(e, "IO exception occured while retrieving file : " + fileName);
                }
            }
        }

    }

    /**
     * This method will retrieve the current paths loaded by the current class loader
     * 
     * @param classLoaderInstance the class from which we will retrieve the class loader that loaded the particular
     *            class
     * @return a list of paths that the class loader is scanning for classes
     */
    public static List<String> retrieveClassFilesByLoader(Class classLoaderInstance) {
        List<String> filePaths = new LinkedList<String>();
        final URLClassLoader ucl = (URLClassLoader) classLoaderInstance.getClassLoader();
        Class clazz = java.net.URLClassLoader.class;
        java.lang.reflect.Field ucp;
        try {
            ucp = clazz.getDeclaredField("ucp");
            ucp.setAccessible(true);
            Object cp = ucp.get(ucl);
            java.lang.reflect.Field loaders = cp.getClass().getDeclaredField("loaders");
            loaders.setAccessible(true);
            java.util.Collection c = (java.util.Collection) loaders.get(cp);

            for (Object clLoader : c) {

                Field[] fields = clLoader.getClass().getDeclaredFields();

                if (fields != null) {
                    for (Field field : fields) {
                        if (field.getName().equals("dir")) {
                            field.setAccessible(true);
                            File file = (File) field.get(clLoader);
                            filePaths.add(file.getCanonicalPath());

                        }
                    }
                }

            }

        } catch (SecurityException e) {
            throw new ClassFinderException(e);
        } catch (NoSuchFieldException e) {
            throw new ClassFinderException(e);
        } catch (IllegalArgumentException e) {
            throw new ClassFinderException(e);
        } catch (IllegalAccessException e) {
            throw new ClassFinderException(e);
        } catch (IOException e) {
            throw new ClassFinderException(e);
        }
        return filePaths;
    }
}
