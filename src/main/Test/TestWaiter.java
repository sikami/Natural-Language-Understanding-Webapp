import com.packagename.myapp.spring.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestWaiter {

    private Waiter waiter;

    @Test
    public void testResultContainsEmotionResult() throws IOException {
        Query query = new Query("Apples and oranges. I love apples! I don't like oranges.", "apples");
        query.setOption("Emotion");

        waiter = new Waiter(query);
        List<Emotion> emotionList = waiter.spitEmotionResponse();
        System.out.println(emotionList);
        assertFalse(emotionList.isEmpty());
    }

    @Test
    public void testResultContainsSyntaxResult() throws IOException {
        Query query = new Query("With great power comes great responsibility", "");
        query.setOption("Syntax");

        waiter = new Waiter(query);
        List<SyntaxResult> syntaxResponse = waiter.spitSyntaxResponse();
        System.out.println(syntaxResponse);
        assertFalse(syntaxResponse.isEmpty());
    }


}
