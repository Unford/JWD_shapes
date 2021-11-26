package test.epam.shape;

import by.epam.shape.comparator.impl.CubeComparators;
import by.epam.shape.entity.Cube;
import by.epam.shape.entity.Point;
import by.epam.shape.repository.Repository;
import by.epam.shape.repository.impl.CubeRepository;
import by.epam.shape.repository.impl.IdCubeSpecification;
import by.epam.shape.repository.impl.VolumeCubeSpecification;
import by.epam.shape.service.CubeService;
import by.epam.shape.service.impl.CubeServiceImpl;

import org.decimal4j.util.DoubleRounder;
import org.testng.annotations.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CubeRepositoryTest {
    private CubeService service = new CubeServiceImpl();
    private Repository repository = new CubeRepository();

    @BeforeTest
    public void init(){
        for (int i = 0; i < 10; i++) {
            repository.add(new Cube(new Point(i,  i / 3, i * 3),
                    ((i + 1) % 2 == 0 ? i + 2 : i / 2) + 1));
        }
    }

    @AfterTest
    public void tearDown(){
        repository.clear();
    }

    @Test(description = "check repository id query",
            dataProvider = "dataForIdQueryTest")
    public void testIdCubeRepositoryQuery(long fromId, long toId, List<Long> expected){
        List<Cube> actual = repository.query(new IdCubeSpecification(fromId, toId));
        assertThat(actual).extracting(cube -> cube.getCubeId()).containsExactlyElementsOf(expected);

    }

    @DataProvider(name = "dataForIdQueryTest")
    public Object[][] dataForIdQueryTest(){
        return new Object[][]{
                {-4, -3, List.of()},
                {0, 2, List.of(1L, 2L)},
                {10, 10, List.of(10L)},
        };
    }

    @Test(description = "check repository volume query",
            dataProvider = "dataForVolumeQueryTest")
    public void testVolumeCubeRepositoryQuery(double fromVolume, double toVolume, List<Double> expected){
        List<Cube> actual = repository.query(new VolumeCubeSpecification(fromVolume, toVolume));
        assertThat(actual).extracting(cube -> DoubleRounder.round(
                        service.calculateCubeVolume(cube).getAsDouble(), 3))
                        .containsExactlyElementsOf(expected);
    }

    @DataProvider(name = "dataForVolumeQueryTest")
    public Object[][] dataForVolumeQueryTest(){
        return new Object[][]{
                {-4.6, -3.6, List.of()},
                {50.0, 400, List.of(64.0, 216.0, 64.0, 125.0)},
        };
    }

    @Test(description = "check repository perimeter sort")
    public void testPerimeterCubeRepositorySort(){
        List<Cube> actual = repository.sort(CubeComparators.PERIMETER.get().reversed());
        List<Double> expected = List.of(144.0, 120.0, 96.0, 72.0, 60.0, 48.0, 48.0, 36.0, 24.0, 12.0);
        assertThat(actual).extracting(cube -> DoubleRounder.round(
                service.calculateCubePerimeter(cube).getAsDouble(), 3))
                .containsExactlyElementsOf(expected);
    }

    @Test(description = "check repository center sort")
    public void testCenterCubeRepositorySort(){
        List<Cube> actual = repository.sort(CubeComparators.CENTER_Y.get());
        List<Double> expected = List.of(0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 2.0, 2.0, 2.0, 3.0);
        assertThat(actual).extracting(cube -> cube.getCenter().y())
                .containsExactlyElementsOf(expected);
    }

}
