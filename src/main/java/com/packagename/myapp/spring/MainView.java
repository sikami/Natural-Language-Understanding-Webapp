package com.packagename.myapp.spring;

import com.vaadin.flow.component.button.Button;
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

import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicReference;

@Route("")
@Scope("prototype")
@StyleSheet("frontend://styles/styles.css")
@Component
public class MainView extends VerticalLayout {

    TextArea textArea;
    RadioButtonGroup<String> analyze;
    TextField keywordField;

    public MainView(@Autowired MessageBean bean) {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("main-layout");

        H1 header = new H1("Natural Language Understanding");
        header.getElement().getThemeList().add("dark");
        add(header);
        H4 poweredBy = new H4("Powered by IBM Watson");
        poweredBy.getElement().getThemeList().add("dark");
        add(poweredBy);
        addTextField();
        addOption();

    }

    private void addTextField() {
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
    }

    private void buttonListener(Button button, String option) {
        //AtomicReference<Label> label = new AtomicReference<>(new Label());
        if (option.equals("Emotion")) {
            button.addClickListener(event -> {
                //label.set(new Label("Emotion is selected"));
                keywordField.setEnabled(false);
                analyze.setEnabled(false);
                textArea.setEnabled(false);
                System.out.println("emotion is selected");
                button.setEnabled(false);
            });
        } else if (option.equals("Syntax")) {
            button.addClickListener(event -> {
                //label.set(new Label("Emotion is selected"));
                analyze.setEnabled(false);
                textArea.setEnabled(false);
                System.out.println("Syntax is selected");
                button.setEnabled(false);
            });
        }
    }





}
