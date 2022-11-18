package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import de.hbrs.se.rabbyte.service.AuthService;

import java.util.List;
import java.util.Map;

@Route("/activate")
public class ActivationView extends Composite implements BeforeEnterObserver {

    private VerticalLayout layout;

    private AuthService authService;

    public ActivationView( AuthService authService) {
        this.authService = authService;
    }
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        try {
            Map<String , List<String>> params =
                    event.getLocation().getQueryParameters().getParameters();
            String token = params.get("token=").get(0);
            authService.activate(token);
            layout.add( new Text("Account activated"));
        } catch (AuthService.AuthException e) {
            layout.add(new Text("Invalid Link"));
        }
    }

    @Override
    protected Component initContent() {
        layout = new VerticalLayout();
        return layout;
    }


}
