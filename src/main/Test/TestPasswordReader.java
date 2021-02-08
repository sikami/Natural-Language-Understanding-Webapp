import com.packagename.myapp.spring.PasswordReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPasswordReader {

    private PasswordReader passwordReader = new PasswordReader();

    @Test
    public void testFileExist() {
        assertTrue(passwordReader.isExist("config"));
    }

    @Test
    public void testFileContainsContent() throws IOException {
        assertTrue(passwordReader.read("config"));

    }
}
