
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.packagename.myapp.spring.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestWatsonService {

    private WatsonService watsonService;

    @Test
    public void testIfWatsonControllerHasMoreThan1Keywords() throws IOException {
        Query query = new Query("I love banana", "love,banana, apple");
        WatsonController watsonController = new WatsonController(query);
        watsonService = new WatsonService(watsonController);
        List<String> result = watsonService.parseKeyword();

        assertTrue(result.size() == 3);

    }

    @Test
    public void testIfKeywordsAreAllLowerCase() throws IOException {
        Query query = new Query("I love banana", "Love,Banana, Apple");
        WatsonController watsonController = new WatsonController(query);

        watsonService = new WatsonService(watsonController);
        List<String> result = watsonService.parseKeyword();
        assertEquals("apple", result.get(2));
    }

    @Test
    public void testIfParseKeywordsDontThrowNull() throws IOException {
        Query query = new Query("I love banana", "");
        WatsonController watsonController = new WatsonController(query);

        watsonService = new WatsonService(watsonController);
        List<String> result = watsonService.parseKeyword();
        assertNotNull(result);
    }

    @Test
    public void testIfWatsonCanConnectIBMAndProduceNotNullResult() throws IOException {
        Query query = new Query("I love banana", "");
        query.setOption("Syntax");
        WatsonController watsonController = new WatsonController(query);
        watsonService = new WatsonService(watsonController);

        AnalysisResults analysisResults = watsonService.connectToWatson();
        assertNotNull(analysisResults);

    }
}
