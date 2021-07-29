import com.packagename.myapp.spring.Query;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMainViewResponse {


    @Test
    public void testTextAreaHasCorrectValue() throws URISyntaxException {
        this.baseUrl = "http://localhost:" + port;
        this.uri = new URI(baseUrl);
        this.query = new Query("Hello, world");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Query> request = new HttpEntity<>(query, headers);
        ResponseEntity<ResponseToGuess> responseToGuessResponseEntity = this.restTemplate.postForEntity(uri, request, ResponseToGuess.class);

        String text = textField.getValue();
    }
}
