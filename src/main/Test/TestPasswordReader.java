import com.packagename.myapp.spring.PasswordReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPasswordReader {

    private PasswordReader passwordReader = new PasswordReader();
    private String path = "src/main/resources/config";

    @Test
    public void testFileExist() {
        assertTrue(passwordReader.isExist(path));
    }

    @Test
    public void testFileContainsContent() throws IOException {
        assertTrue(passwordReader.read(path));

    }
}
