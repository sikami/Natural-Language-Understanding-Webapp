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

    //Test will fail if using different API Key, as different API key has different token. So best to curl the token
    //and calculate the length of the token then add it in method below
    @Test
    public void testIfAccessTokenIsPresent() throws IOException {
        passwordReader.read(path);
        assertEquals(1459, passwordReader.getAccessToken().length());
    }
}
