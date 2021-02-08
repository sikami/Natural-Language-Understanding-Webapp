package com.packagename.myapp.spring;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.radiobutton.GeneratedVaadinRadioButton;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.form.RadioButtonTag;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

import static com.vaadin.flow.component.Tag.P;

@Route("")
@StyleSheet("frontend://styles/styles.css")
public class MainView extends VerticalLayout {

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

    }

    private void addTextField() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        addClassName("textField");
        Label textFieldLabel = new Label("Please insert your text below:");
        add(textFieldLabel);

        TextArea textArea = new TextArea();
        textArea.setSizeFull();
        add(textArea);
        RadioButtonGroup<String> analyze = new RadioButtonGroup<>();
        analyze.setItems("Emotion", "Syntax");

        Label analyzeOption = new Label("Analyze for:");
        add(analyzeOption);
        add(analyze);
        Label keywordLabel = new Label("Enter your keyword(s) below. If you have more" +
                " than one keyword, please separate them with comma.");

        TextField keywordField = new TextField();
        keywordField.setWidth("300px");
        Button button = new Button("Analyze and show result");
        button.getElement().getThemeList().add("primary");
        analyze.addValueChangeListener(event -> {
           if (event.getValue().equals("Emotion")) {
               remove(button);
               add(keywordLabel);
               add(keywordField);
               add(button);
           } else {
               remove(button);
               remove(keywordLabel);
               remove(keywordField);
               add(button);
           }
        });


    }





}
