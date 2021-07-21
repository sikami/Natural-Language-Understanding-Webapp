
import com.packagename.myapp.spring.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWatsonService {

    @Test
    public void testIfCanConnectToWatsonForEmotion() {
        assertTrue(WatsonService.connectToWatson("Emotion"));
    }

    @Test
    public void testIfCanConnectToWatsonForSyntax() {
        assertTrue(WatsonService.connectToWatson("Syntax"));
    }

    @Test
    public void testIfCannotConnectToWatsonOtherThanSyntaxAndEmotion() {
        assertFalse(WatsonService.connectToWatson("Happiness"));
    }

    @Test
    public void testIfWatsonControllerHasMoreThan1Keywords() {
        Query query = new Query("I love banana", "love,banana");
        WatsonController watsonController = new WatsonController(query);

    }
}
