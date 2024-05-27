package com.example.chat.pages;

import com.example.chat.entity.UserData;
import com.example.chat.server.Server;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
@CssImport("./style.css")
public class LoginPage extends VerticalLayout {
    private TextField loginField;
    private PasswordField passwordField;
    private ComboBox<String> encryptionAlgorithmComboBox;
    private Server server;
//
    public LoginPage(Server server) {
        this.server = server;
        // Задаем классы стилей для элементов
        addClassName("login-page");

        loginField = new TextField("Логин");
        passwordField = new PasswordField("Пароль");

        encryptionAlgorithmComboBox = new ComboBox<>("Алгоритм шифрования");
        encryptionAlgorithmComboBox.setItems("RC6", "Serpent");
        encryptionAlgorithmComboBox.setValue("RC6");

        Button loginButton = new Button("Войти", this::onLoginButtonClick);

        Div formWrapper = new Div();
        formWrapper.addClassName("form-wrapper");

        formWrapper.add(loginField, passwordField, encryptionAlgorithmComboBox, loginButton);

        add(formWrapper);
    }

    private void onLoginButtonClick(ClickEvent<Button> event) {
        String username = loginField.getValue();
        String nameAlgorithm = encryptionAlgorithmComboBox.getValue();
        String password = passwordField.getValue();

        if (username == null || username.isEmpty()) {
            Notification.show("Логин не может быть пустым");
            return;
        }

        if (password == null || password.isEmpty()) {
            Notification.show("Пароль не может быть пустым");
            return;
        }

        if (nameAlgorithm == null || nameAlgorithm.isEmpty()) {
            Notification.show("Алгоритм шифрования не выбран");
            return;
        }

        try {
            UserData userData = server.login(username, nameAlgorithm);
            if(userData != null) {
                Notification.show("Logging in as " + username + " using " + nameAlgorithm + " encryption.");
                UI.getCurrent().navigate(String.valueOf(userData.getId()));
            } else {
                Notification.show("Не удалось выполнить вход");
            }
        } catch (Exception e) {
            Notification.show("Login failed: " + e.getMessage());
        }
    }
}

