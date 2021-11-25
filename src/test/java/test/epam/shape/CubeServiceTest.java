package test.epam.shape;

import by.epam.shape.entity.Cube;
import by.epam.shape.entity.Point;
import by.epam.shape.service.CubeService;
import by.epam.shape.service.impl.CubeServiceImpl;
import org.assertj.core.data.Offset;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CubeServiceTest {
    private CubeService service = new CubeServiceImpl();
    private Cube cubeFirstExample = new Cube(new Point(1.4, 2.3, 4.5), 6.3);
    private Cube cubeSecondExample = new Cube(new Point(5, 5, 5), 10);
    private Cube cubeThirdExample = new Cube(new Point(54.4, -21.3, 65.5), 51.52);

    @Test(dataProvider = "dataForPerimeterTest")
    public void testPerimeterCalculation(Cube cube, double expected){
        double actual = service.calculateCubePerimeter(cube).getAsDouble();
        assertThat(actual).isCloseTo(expected, Offset.offset(0.001));
    }

    @DataProvider(name = "dataForPerimeterTest")
    public Object[][] dataForPerimeterTest(){
        return new Object[][]{
                {cubeFirstExample, 75.6},
                {cubeSecondExample, 120},
                {cubeThirdExample, 618.24},
        };
    }

    @Test(dataProvider = "dataForSurfaceAreaTest")
    public void testSurfaceAreaCalculation(Cube cube, double expected){
        double actual = service.calculateCubeSurfaceArea(cube).getAsDouble();
        assertThat(actual).isCloseTo(expected, Offset.offset(0.001));
    }

    @DataProvider(name = "dataForSurfaceAreaTest")
    public Object[][] dataForSurfaceAreaTest(){
        return new Object[][]{
                {cubeFirstExample, 238.14},
                {cubeSecondExample, 600},
                {cubeThirdExample, 15925.8624},
        };
    }

    @Test(dataProvider = "dataForVolumeTest")
    public void testVolumeCalculation(Cube cube, double expected){
        double actual = service.calculateCubeVolume(cube).getAsDouble();
        assertThat(actual).isCloseTo(expected, Offset.offset(0.001));
    }

    @DataProvider(name = "dataForVolumeTest")
    public Object[][] dataForVolumeTest(){
        return new Object[][]{
                {cubeFirstExample, 250.047},
                {cubeSecondExample, 1000},
                {cubeThirdExample, 136750.0718},
        };
    }

    @Test(dataProvider = "dataForRatioSectionTest")
    public void testRatioSectionCalculation(Cube cube, double shift, double expected){
        double actual = service.calculateRatioSectionOxy(cube, shift).getAsDouble();
        assertThat(actual).isCloseTo(expected, Offset.offset(0.001));
    }

    @DataProvider(name = "dataForRatioSectionTest")
    public Object[][] dataForRatioSectionTest(){
        return new Object[][]{
                {cubeFirstExample, 3, 2.818},
                {cubeSecondExample, 5, 1},
                {cubeThirdExample, -3,-2.205},
        };
    }

    @Test(dataProvider = "dataForTangentToOxyTest")
    public void testTangentToOxy(Cube cube, boolean expected){
        boolean actual = service.isCubeTangentToOxy(cube);
        assertThat(actual).isEqualTo(expected);
    }

    @DataProvider(name = "dataForTangentToOxyTest")
    public Object[][] dataForTangentToOxyTest(){
        return new Object[][]{
                {cubeFirstExample, false},
                {cubeSecondExample, true},
                {cubeThirdExample, false},
        };
    }
}
