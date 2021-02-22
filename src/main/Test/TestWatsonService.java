
import com.packagename.myapp.spring.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWatsonService {

    @Test
    public void testIfCanConnectToWatson() {
        assertTrue(WatsonService.connectToWatson("Emotion"));
    }
}
