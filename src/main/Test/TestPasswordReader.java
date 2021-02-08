import com.packagename.myapp.spring.PasswordReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPasswordReader {
    @Test
    public void testFileExist() {
        PasswordReader passwordReader = new PasswordReader();
        assertTrue(passwordReader.read("config"));
    }
}
