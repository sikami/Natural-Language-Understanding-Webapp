import com.packagename.myapp.spring.MainView;
import com.packagename.myapp.spring.Query;
import com.packagename.myapp.spring.Waiter;
import com.packagename.myapp.spring.WatsonService;
import com.vaadin.flow.component.textfield.TextArea;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TestMainView {

    private static MainView mockedMainView;
    private static TextArea mockedTextArea;
    private TextArea mockedKeywordField;
    private Query query;
    private Waiter waiter;
    //not mocking Result area first because it hasnt connected with Watson
    //TODO: need to refactor this as Query has not been attached to MainView class yet.


//    @BeforeAll
    @BeforeAll
    public static void setup() {
        mockedMainView = mock(MainView.class);
        mockedTextArea = new TextArea();
    }

    @Test
    public void testIfTextFieldRetrieviesCorrectText() {
        mockedTextArea.setValue("I love banana and apples");
        when(mockedMainView.getTextArea()).thenReturn(mockedTextArea);
        Assertions.assertEquals("I love banana and apples", mockedMainView.getTextArea().getValue());

    }


}

