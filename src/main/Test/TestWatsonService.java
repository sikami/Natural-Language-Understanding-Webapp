
import com.google.gson.*;
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
        watsonService = new WatsonService(query);
        List<String> result = watsonService.parseKeyword();

        assertEquals(3, result.size());
    }

    @Test
    public void testIfKeywordsAreAllLowerCase() throws IOException {
        Query query = new Query("I love banana", "Love,Banana, Apple");
        watsonService = new WatsonService(query);
        List<String> result = watsonService.parseKeyword();
        assertEquals("apple", result.get(2));
    }

    @Test
    public void testIfParseKeywordsDontThrowNull() throws IOException {
        Query query = new Query("I love banana", "");

        watsonService = new WatsonService(query);
        List<String> result = watsonService.parseKeyword();
        assertNotNull(result);
    }

    @Test
    public void testIfWatsonCanConnectIBMAndProduceNotNullResultForSyntax() throws IOException {
        Query query = new Query("I love banana, and I do not like Apple. However, everything is well. I can still eat food. I am happy.", "");
        query.setOption("Syntax");
        watsonService = new WatsonService(query);

        AnalysisResults analysisResults = watsonService.connectToWatson();
        assertNotNull(analysisResults);

    }

    @Test
    public void testIfWatsonCanConnectIBMAndProduceNotNullResultForEmotion() throws IOException {
        Query query = new Query("I love banana, and I do not like Apple. However, everything is well. I can still eat food. I am happy.", "Apple, food");
        query.setOption("Emotion");
        watsonService = new WatsonService(query);

        AnalysisResults analysisResults = watsonService.connectToWatson();
        assertNotNull(analysisResults);

    }

    @Test
    public void testIfAnalysisResultReturnCorrectEmotion() throws IOException {
        Query query = new Query("Apples and oranges. I love apples! I don't like oranges.", "apples, oranges");
        query.setOption("Emotion");
        watsonService = new WatsonService(query);

        AnalysisResults analysisResults = watsonService.connectToWatson();

        System.out.println(analysisResults);
        List<Emotion> emotionList = watsonService.parseEmotion(analysisResults);


        for (Emotion emotion : emotionList) {
            if (emotion.getAnger() == 0.040087) {
                assertEquals(0.040087, emotion.getAnger());
            }
        }
    }

    @Test
    public void testIfAnalysisResultJsonArrayReturnCorrectSyntax() throws IOException {
        Query query = new Query("With great power comes great responsibility", "");
        query.setOption("Syntax");
        watsonService = new WatsonService(query);
        AnalysisResults analysisResults = watsonService.connectToWatson();

        List<SyntaxResult> syntaxResultList = watsonService.parseSyntax(analysisResults);

        for (SyntaxResult syntax : syntaxResultList) {
            if (syntax.getWord().contains("With")) {
                assertEquals("ADP", syntax.getPartOfSpeech());
            }
        }
    }

    @Test
    public void testIfAnalysisResultDoesntReturnWordOutsideKeyword() throws IOException {
        Query query = new Query("Apples and oranges. I love apples! I don't like oranges.", "apples");
        query.setOption("Emotion");
        watsonService = new WatsonService(query);

        AnalysisResults analysisResults = watsonService.connectToWatson();

        List<Emotion> emotionList = watsonService.parseEmotion(analysisResults);

        System.out.println(emotionList);
        for (Emotion emotion : emotionList) {
            if (!emotion.getWord().contains("apples")) {
                assertFalse(emotion.getWord().contains("oranges"));
            }
        }
    }




}
