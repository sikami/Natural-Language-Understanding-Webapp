import com.packagename.myapp.spring.Query;
import com.packagename.myapp.spring.WatsonController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWatsonController {

    private WatsonController watsonController;
    private Query query;

    @BeforeEach
    public void startTestWatsonController() {
        query = new Query("I love banana", "banana");
        watsonController = new WatsonController(query);
    }


    @Test
    public void testIfQueryHasKeywords() {
        assertTrue(watsonController.getKeyword().length() > 0);
    }

    @Test
    public void testIfQueryHasText() {
        assertTrue(watsonController.getText().length() > 0);
    }

    @Test
    public void testIfQueryDontHaveText() {
        query.setText("");
        assertTrue(watsonController.getText().length() == 0);
    }

    @Test
    public void testIfQueryDontHaveKeywords() {
        query.setKeywords("");
        assertTrue(watsonController.getKeyword().length() == 0);
    }


}
