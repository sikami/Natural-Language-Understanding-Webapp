import com.packagename.myapp.spring.Query;
import com.packagename.myapp.spring.WatsonService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestWaiter {

    private WatsonService watsonService;
    private Query query;

    @Test
    public void testResultContainsResult() throws IOException {
        this.watsonService = new WatsonService(query);

    }
}
