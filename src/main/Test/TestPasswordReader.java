import com.packagename.myapp.spring.PasswordReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void testIfFirstLineIsApiKey() throws IOException {
        passwordReader.read(path);
        assertEquals(44, passwordReader.getApiKey().length());
    }

    @Test
    public void testIfAccessTokenIsPresent() throws IOException {
        passwordReader.read(path);
        assertEquals(1748, passwordReader.getAccessToken().length());
    }
}
