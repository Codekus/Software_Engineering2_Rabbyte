package de.hbrs.se.rabbyte.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import de.hbrs.se.rabbyte.control.ActivationControl;
import de.hbrs.se.rabbyte.dtos.ActivationResultDTO;
import de.hbrs.se.rabbyte.service.AuthService;
import de.hbrs.se.rabbyte.util.Globals;
import de.hbrs.se.rabbyte.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

@Route("activate")
@PageTitle(Globals.PageTitle.ACTIVATE)
@Theme(value = Lumo.class)
public class ActivationView extends VerticalLayout implements BeforeEnterObserver {

    private VerticalLayout layout;

    private AuthService authService;

    Map<String , List<String>> params;

    private ActivationResultDTO activationResultDTO;

    @Autowired
    private ActivationControl activationControl;

    public ActivationView( AuthService authService) {
        this.authService = authService;
    }
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
            params = event.getLocation().getQueryParameters().getParameters();



    }

    public ActivationView() {

        layout = new VerticalLayout();

        H1 h1 = new H1();
        h1.setText("Aktiviere deinen Account");

        Label infoText = new Label("Um Ihr Konto zu aktivieren, klicken Sie bitte auf die unten stehende Taste");

        Button button = new Button();
        button.setText("Aktiviere Account");
        button.addClickListener( e -> {
            String token = params.get("token").get(0);


            activationResultDTO = activationControl.activate(token);

            if(activationResultDTO.getActivationResult()) {
                Utils.triggerDialogMessage("Success" , ":)");
            } else {
                Utils.triggerDialogMessage("Failure!" , ":(");
            }


        });


        layout.add(h1 , infoText , button);
        layout.setPadding(true);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setAlignSelf(FlexComponent.Alignment.CENTER);

        add(layout);

        }
    }



