package com.example.chat.pages;

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
//import ru.mai.javachatservice.server.ChatServer;

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

    public LoginPage() {
        // Задаем классы стилей для элементов
        addClassName("login-page");

        TextField loginField = new TextField("Логин");
        PasswordField passwordField = new PasswordField("Пароль");

        ComboBox<String> encryptionAlgorithmComboBox = new ComboBox<>("Алгоритм шифрования");
        encryptionAlgorithmComboBox.setItems("RC6", "Serpent");
        encryptionAlgorithmComboBox.setValue("RC6");

        Button loginButton = new Button("Войти", this::onLoginButtonClick);

        // Обертка div
        Div formWrapper = new Div();
        formWrapper.addClassName("form-wrapper");

        // Добавляем элементы в обертку
        formWrapper.add(loginField, passwordField, encryptionAlgorithmComboBox, loginButton);

        // Добавляем обертку на страницу
        add(formWrapper);
    }

    private void onLoginButtonClick(ClickEvent<Button> event) {
        Notification.show("Logging in...");
    }
}

