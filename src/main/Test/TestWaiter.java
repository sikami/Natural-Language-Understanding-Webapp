import com.packagename.myapp.spring.Emotion;
import com.packagename.myapp.spring.Query;
import com.packagename.myapp.spring.Waiter;
import com.packagename.myapp.spring.WatsonService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestWaiter {

    private Waiter waiter;

    @Test
    public void testResultContainsResult() throws IOException {
        Query query = new Query("Apples and oranges. I love apples! I don't like oranges.", "apples");
        query.setOption("Emotion");

        waiter = new Waiter(query);
        List<Emotion> emotionList = waiter.spitEmotionResponse();
        System.out.println(emotionList);
        assertFalse(emotionList.isEmpty());
    }
}
