package com.packagename.myapp.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Route("")
@Scope("prototype")
@StyleSheet("frontend://styles/styles.css")
@HtmlImport("frontend://styles/vaadin-config.html")
@Component
public class MainView extends VerticalLayout {

    private TextArea textArea;
    private TextArea resultArea;
    private RadioButtonGroup<String> analyze;
    private TextField keywordField;
    private Waiter waiter;


    @Autowired
    private Query query;

    public MainView(@Autowired MessageBean bean) {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("main-layout");
        addTextField();
        addOption();

    }

    private void addTextField() {
        H1 header = new H1("Natural Language Understanding");
        header.getElement().getThemeList().add("dark");
        add(header);
        H4 poweredBy = new H4("Powered by IBM Watson");
        poweredBy.getElement().getThemeList().add("dark");
        add(poweredBy);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        addClassName("textField");
        Label textFieldLabel = new Label("Please insert your text below:");
        add(textFieldLabel);

        textArea = new TextArea();
        textArea.setSizeFull();
        add(textArea);

    }

    private void addOption() {
        analyze = new RadioButtonGroup<>();
        analyze.setItems("Emotion", "Syntax");

        Label analyzeOption = new Label("Analyze for:");
        add(analyzeOption);
        add(analyze);
        Label keywordLabel = new Label("Enter your keyword(s) below. If you have more" +
                " than one keyword, please separate them with comma.");

        keywordField = new TextField();
        keywordField.setWidth("300px");
        Button button = new Button("Analyze and show result");
        button.getElement().getThemeList().add("primary");

        analyze.addValueChangeListener(event -> {
            if (event.getValue().equals("Emotion")) {
                remove(button);
                add(keywordLabel);
                add(keywordField);
                add(button);
                buttonListener(button, "Emotion");

            } else {
                remove(button);
                remove(keywordLabel);
                remove(keywordField);
                add(button);
                buttonListener(button, "Syntax");
            }
        });

        button.addClickListener(event -> {
            this.query = new Query(textArea.getValue(), keywordField.getValue());
            this.query.setOption(analyze.getValue());
            try {
                addResult();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    private void buttonListener(Button button, String option) {
        if (option.equals("Emotion")) {
            button.addClickListener(event -> {

                //need to find away on how to make exception when no keywords, without showing text field in the bottom
                if (keywordField.isEmpty()) {
                    resultArea.setVisible(false);
                    System.out.println("no keyword");
                    keywordField.setPlaceholder("Please insert keyword/s");

                } else {
                    resultArea.setVisible(true);
                    keywordField.setEnabled(false);
                    analyze.setEnabled(false);
                    textArea.setEnabled(false);
                    System.out.println("emotion is selected");
                    button.setEnabled(false);
                    query.setOption("Emotion");
                }

            });
        } else if (option.equals("Syntax")) {
            button.addClickListener(event -> {
                analyze.setEnabled(false);
                textArea.setEnabled(false);
                System.out.println("Syntax is selected");
                button.setEnabled(false);
                query.setOption("Syntax");
            });
        }
    }

    private void addResult() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        resultArea = new TextArea();
        resultArea.setSizeFull();
        add(resultArea);
        resultArea.setValue(query.getText() + " keyword: " + query.getKeyword() + ", option: " + query.getOption());

        this.waiter = new Waiter(query);

        if (query.getOption().contains("Syntax")) {
            this.waiter.spitSyntaxResponse().forEach(stringBuilder::append);
        } else {
            System.out.println("inside addResult");
            this.waiter.spitEmotionResponse().forEach(stringBuilder::append);
        }
        resultArea.setValue(stringBuilder.toString());

    }

    public TextArea getTextArea() {
        return textArea;
    }

    public TextArea getResultArea() {
        return resultArea;
    }

    public TextField getKeywordField() {
        return keywordField;
    }
}
