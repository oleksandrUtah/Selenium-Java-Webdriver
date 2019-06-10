package parametersTestsExamples;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataproviderClass {
    @DataProvider(name = "login-password")
    //to make data provider method as static:

    public static Object[][] getDataFromProvider(Method m){

        if (m.getName().equalsIgnoreCase("testMethod1")){
            return new Object[][]{
                    {"koz84075+007@gmail.com", "1234567a"}
            };
        }
        else if (m.getName().equalsIgnoreCase("testMethod2")){
            return new Object[][]{
                    {"koz84075+007@gmail.com", "1234567a"}
            };
        }
        else if (m.getName().equalsIgnoreCase("testMethod3")){
            return new Object[][]{
                    {"Koz84075+007@gmail.com", "1234567a"}
            };
        }
        else {
            return new Object[][]{
                    {"koz84075+007@gmail.com", "1234567A"},
                    {"koz84075+007@gmail.com", "123a"},
                    {"xxxxx", "1234567a"},
                    {"", "1234567a"},
                    {"koz84075+007@gmail.com", ""}
            };
        }
    }
}


