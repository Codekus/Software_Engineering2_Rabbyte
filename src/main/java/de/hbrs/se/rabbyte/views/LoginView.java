package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.hbrs.se.rabbyte.exception.AuthException;

import de.hbrs.se.rabbyte.security.SecurityService;
import de.hbrs.se.rabbyte.util.NavigationUtil;
import de.hbrs.se.rabbyte.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


@Route("login")
@PageTitle("Login | Vaadin CRM")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();

    @Autowired
    SecurityService securityService;

    public LoginView(){
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.addLoginListener(event -> {
            try {
                securityService.authenticate(event.getUsername(), event.getPassword());
                UI.getCurrent().navigate("");
            } catch (AuthException e) {
                login.setError(true);
                if(e.getMessage().equals("Der Account ist noch nicht aktiviert")){
                    Utils.triggerDialogMessage("Dieser Account wurde noch nicht aktiviert" , "Bitte aktivieren sie ihren Account, indem sie den Anweisungen in ihrer Registrierung-Email folgen");
                } else {
                    login.setError(true);
                    Notification.show("Wrong credentials");
                }
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        });
        Button button = new Button("Konto Erstellen!");
        Paragraph info = new Paragraph("Sie haben noch kein Rabbyte-Konto?");

        button.addClickListener(clickEvent -> NavigationUtil.toRegisterView());
        button.setHeight("10px");

        add(new H1("Herzlich Willkommen"), login, info, button);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // inform the user about an authentication error
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }


    }
}