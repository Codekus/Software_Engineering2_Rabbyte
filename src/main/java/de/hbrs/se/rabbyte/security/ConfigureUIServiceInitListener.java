package de.hbrs.se.rabbyte.security;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.RouteConfiguration;
import de.hbrs.se.rabbyte.views.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
        if (!LoginView.class.equals(event.getNavigationTarget())
                && !SecurityUtils.isUserLoggedIn()) {
            if(event.getLocation().getSegments().get(0).equals("registration")){
                return;
            }
            if(event.getLocation().getSegments().get(0).equals("verification")){
                return;
            }
            event.rerouteTo(LoginView.class);
        }
        else if((event.getLocation().getSegments().get(0).equals("login")
                || event.getLocation().getSegments().get(0).equals("registration") ||
                !RouteConfiguration.forSessionScope().isPathRegistered(event.getLocation().getSegments().get(0))) &&
                SecurityUtils.isUserLoggedIn()){
            event.rerouteTo("");
        }

 



    }
}