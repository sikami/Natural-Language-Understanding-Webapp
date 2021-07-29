
import com.packagename.myapp.spring.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestWatsonService {

    private WatsonService watsonService;

    @Test
    public void testIfCanConnectToWatsonForEmotion() {
        assertTrue(WatsonService.optionChooser("Emotion"));
    }

    @Test
    public void testIfCanConnectToWatsonForSyntax() {
        assertTrue(WatsonService.optionChooser("Syntax"));
    }

    @Test
    public void testIfCannotConnectToWatsonOtherThanSyntaxAndEmotion() {
        assertFalse(WatsonService.optionChooser("Happiness"));
    }

    @Test
    public void testIfWatsonControllerHasMoreThan1Keywords() {
        Query query = new Query("I love banana", "love,banana, apple");
        WatsonController watsonController = new WatsonController(query);

        this.watsonService = new WatsonService(watsonController);
        List<String> result = watsonService.parseKeyword();

        assertTrue(result.size() == 3);

    }

    @Test
    public void testIfKeywordsAreAllLowerCase() {
        Query query = new Query("I love banana", "Love,Banana, Apple");
        WatsonController watsonController = new WatsonController(query);

        this.watsonService = new WatsonService(watsonController);
        List<String> result = watsonService.parseKeyword();
        assertEquals("apple", result.get(2));
    }

    @Test
    public void testIfParseKeywordsDontThrowNull() {
        Query query = new Query("I love banana", "");
        WatsonController watsonController = new WatsonController(query);

        this.watsonService = new WatsonService(watsonController);
        List<String> result = watsonService.parseKeyword();
        assertNotNull(result);
    }


}
