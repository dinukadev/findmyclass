findmyclass
===========

This library will allow you to search for class files given a package and also gives the ability to search for classes annotated with a specific annotation.


Example
===========

ClassFinderService finder = new ClassFinderServiceImpl();

//Retrieves all the classes found within the given package
List<String>classesFound = finder.findClassesByPackage("com.mypackage");

//Retrieves all classes annotated with TestAnnotation which could be on the class/field/method level
List<String>annotatedClassesFound = finder.findAnnotatedClassesInPackage("com.mypackage",TestAnnotation.class);
