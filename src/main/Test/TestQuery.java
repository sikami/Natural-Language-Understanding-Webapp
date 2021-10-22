import com.packagename.myapp.spring.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuery {

    private Query query;

    @BeforeEach
    public void startTestWatsonController() {
        query = new Query("I love banana", "banana");
    }

    @Test
    public void testIfCanConnectToWatsonForSyntax() {
        query.setOption("Syntax");
        assertEquals("Syntax", query.getOption());
    }

    @Test
    public void testIfCanConnectToWatsonForEmotion() {
        query.setOption("Emotion");
        assertEquals("Emotion", query.getOption());
    }

    @Test
    public void testIfQueryHasKeywords() {
        assertTrue(query.getKeyword().length() > 0);
    }

    @Test
    public void testIfQueryHasText() {
        assertTrue(query.getText().length() > 0);
    }

    @Test
    public void testIfQueryDontHaveText() {
        query.setText("");
        assertTrue(query.getText().length() == 0);
    }

    @Test
    public void testIfQueryDontHaveKeywords() {
        query.setKeywords("");
        assertTrue(query.getKeyword().length() == 0);
    }


}
