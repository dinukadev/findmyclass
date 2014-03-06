package test.com.ezsolutionz.api;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import test.com.ezsolutionz.annotation.TestAnnotation;

import com.ezsolutionz.api.ClassFinderService;
import com.ezsolutionz.api.impl.ClassFinderServiceImpl;

/**
 * The test class to verify the functionality of the library
 * 
 * @author Dinuka Arseculeratne
 *
 */
public class ClassFinderTest extends TestCase {

    private ClassFinderService classFinderService = null;

    public void setUp() {
        classFinderService = new ClassFinderServiceImpl();
    }

    public void testFindClassesByPackage() {
        List<String> fileList = classFinderService.findClassesByPackage("com.ezsolutionz");
        Assert.assertTrue(fileList.size() > 0);
    }

    public void testFindAnnotatedClassByPackage() {
        List<String> classFilePaths = classFinderService.findAnnotatedClassesInPackage("com", TestAnnotation.class);
        Assert.assertTrue(classFilePaths.size() > 0);
    }
}
