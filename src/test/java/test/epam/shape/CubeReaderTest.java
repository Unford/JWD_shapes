package test.epam.shape;

import by.epam.shape.exception.CubeException;
import by.epam.shape.reader.CubeReader;
import by.epam.shape.reader.impl.CubeReaderImpl;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class CubeReaderTest {
    private CubeReader reader = new CubeReaderImpl();;

    @Test(description = "read all lines in correct file",
            dataProvider = "dataForCorrectLineReader")
    public void testReadAllCorrectLines(String filepath, List<String> expected)throws CubeException {
        List<String> actual = reader.readAllLines(filepath);
        assertThat(actual).containsExactlyElementsOf(expected);

    }

    @DataProvider(name = "dataForCorrectLineReader")
    public Object[][] dataForCorrectLineReader(){
        return new Object[][]{
                {"resources/input/empty.txt", List.of()},
                {"resources/input/input.txt", List.of("    1.3 4.2 5.1 6.2",
                        "h1.2 3.2 31.1 4.1 4.1",
                        "3.1 5.1 3.2    1.3",
                        "1.4 2.3 4.5 6.3",
                        "1.4 3.3 1.5 3.3s",
                        "45465ffg")},
        };
    }

    @Test(description = "test reader throw cube exception",
            dataProvider = "dataForIncorrectReader",
            expectedExceptions = CubeException.class)
    public void testReadAllException(String filepath)throws CubeException {
        reader.readAllLines(filepath);
    }

    @DataProvider(name = "dataForIncorrectReader")
    public Object[][] dataForIncorrectReader(){
        return new Object[][]{
                {"resources/input/notExist.txt"},
                {"resources/input/\\escapeChar.txt"},
        };
    }

}
