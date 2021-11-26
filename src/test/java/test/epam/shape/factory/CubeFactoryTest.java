package test.epam.shape.factory;

import by.epam.shape.entity.Cube;
import by.epam.shape.entity.Point;
import by.epam.shape.factory.CubeFactory;
import by.epam.shape.factory.impl.CubeFactoryImpl;

import org.testng.annotations.*;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CubeFactoryTest {
    private CubeFactory factory = new CubeFactoryImpl();

    @Test(description = "creating cube from valid parameters",
            dataProvider = "dataForCorrectCubeFactory")
    public void testCorrectCubeFactory(Double[] parameters, Cube expected){
        Optional<Cube> actual = factory.createCube(parameters);
        assertThat(actual.get())
                .extracting(Cube::getCenter, Cube::getEdge)
                .containsExactly(expected.getCenter(), expected.getEdge());
    }

    @DataProvider(name = "dataForCorrectCubeFactory")
    public Object[][] dataForCorrectCubeFactory(){
        return new Object[][]{
                {new Double[]{1.3, 4.2, 5.2, 4.3},
                        new Cube(new Point(1.3, 4.2, 5.2), 4.3)},

                {new Double[]{1.1, 2.2, 3.3, 4.4, 5.5, 6.6},
                        new Cube(new Point(1.1, 2.2, 3.3), 4.4)},
        };
    }

    @Test(description = "creating cube from invalid parameters",
            dataProvider = "dataForIncorrectCubeFactory")
    public void testIncorrectCubeFactory(Double[] parameters){
        Optional<Cube> actual = factory.createCube(parameters);
        assertThat(actual).isEmpty();
    }

    @DataProvider(name = "dataForIncorrectCubeFactory")
    public Object[][] dataForDoubleValidation(){
        return new Object[][]{
                {new Double[]{11.322, 43.52, 15.2, -4.3}},
                {null},
                {new Double[]{1.1, 2.2}},
        };
    }

}
