import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.packagename.myapp.spring.EmotionResult;
import org.junit.jupiter.api.Test;

public class TestEmotionResult {

    private EmotionResult emotionResult;

   @Test
    public void testIfClassContainsNonAlphabet() {
       emotionResult = new EmotionResult(0.2,0.1,0.6,0.7,0.3);

   }

    @Test
    public void testIfValueIsFloat() {

   }


}
