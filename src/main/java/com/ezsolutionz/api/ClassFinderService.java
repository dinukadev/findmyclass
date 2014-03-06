package com.ezsolutionz.api;

import java.io.File;
import java.util.List;

/**
 * The API lets you find classes on a given package and lets you also retrieve classes that consists of annotations on a
 * class level. Note that in order to search the specified classes, it should be present in the application classpath in
 * order for the classes to be recovered
 * 
 * @author Dinuka Arseculeratne
 */
public interface ClassFinderService {
	
	/**
	 * This method will allow you to pass in a package and will give you all the classes
	 * found in that particular package. Note that to retrieve the classes, it is assumed
	 * that the classes you want to search are already included in the class path of the 
	 * application using this library
	 * 
	 * @param packagePath the package path in the format of e.g : com.ezsoluionz
	 * @return the .class files found on the passed in package
	 */
	List<String> findClassesByPackage(final String packagePath);
	
	/**
	 * This method will find all classes annotated with a specific annotation class on the given package
	 * path. Note that this method will search for the annotation on class,method and field level
	 * 
	 * 
	 * @param packagePath the package path by which you want to search for the annotated class
	 * @param the annotated {@link Class} class instace to search from 
	 * @return a list of class names with their fully qualified class names that has the annotation present
	 */
	List<String> findAnnotatedClassesInPackage(final String packagePath,final Class annotatedClass);
	
	
}
