package test.epam.shape.warehouse;

import by.epam.shape.entity.Cube;
import by.epam.shape.entity.CubeParameters;
import by.epam.shape.entity.CubeWarehouse;
import by.epam.shape.entity.Point;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class CubeWarehouseTest {
    private CubeWarehouse warehouse = CubeWarehouse.getInstance();

    @Test
    public void testCubeCreateObserver(){
        for (int i = 0; i < 5; i++) {
            new Cube(null, 0);
        }
        Map<Long, CubeParameters> actual = warehouse.getCubeParametersMap();
        List<Long> expected = List.of(1L, 2L, 3L, 4L, 5L);
        assertThat(actual).containsOnlyKeys(expected);
    }

    @Test
    public void testCubeSetObserver(){
        Cube cube = new Cube(new Point(0.0, 0.0, 0.0), 1);
        cube.setEdge(3.0);
        CubeParameters actual = warehouse.getParameters(cube.getCubeId()).get();
        CubeParameters expected = new CubeParameters(54.0, 27.0, 36.0);
        Assert.assertEquals(actual, expected);
    }

    @AfterTest
    public void tearDown(){
        warehouse.clear();
    }

}
