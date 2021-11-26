package test.epam.shape;

import by.epam.shape.validator.CubeValidator;
import by.epam.shape.validator.impl.CubeValidatorImpl;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;




public class CubeValidatorTest {
    private CubeValidator validator = new CubeValidatorImpl();

    @Test(description = "Validation cube strings",
            dataProvider = "dataForCubeLineValidation")
    public void testCubeLineValidation(String lineWithParameters, boolean expected){
        boolean actual = validator.isValidCubeLine(lineWithParameters);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForCubeLineValidation")
    public Object[][] dataForCubeLineValidation(){
        return new Object[][]{
                {"1.3 4.2 5.1 6.2", true},
                {"h1.2 3.2 31.1 4.1 4.1", false},
                {"3.1 -5.312 77.2 1.3e+2", true},
                {"45465ffg", false},
                {"31.4 +3.3 1.5E-3 -3.3", false},
                {"1 3 -4 5", true},
                {"1          3.2      -12.7      +5", true},
        };
    }

    @Test(description = "Validation string with double",
            dataProvider = "dataForDoubleValidation")
    public void testDoubleValidation(String lineWithDouble, boolean expected){
        boolean actual = validator.isValidDouble(lineWithDouble);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForDoubleValidation")
    public Object[][] dataForDoubleValidation(){
        return new Object[][]{
                {"341.00123", true},
                {" 3.2 ", false},
                {"-1.3e+2", true},
                {"+4", true},
                {"2..4", false},
                {"-5.23E-4", true},
                {"-530.00100", true},
        };
    }

}
