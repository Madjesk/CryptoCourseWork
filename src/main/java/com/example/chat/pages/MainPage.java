package com.example.chat.pages;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;

public class MainPage extends VerticalLayout implements HasUrlParameter<String> {
    private long userId;
    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        this.userId = Long.parseLong(parameter);
    }

    public


}
