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
import de.hbrs.se.rabbyte.control.VerificationControl;
import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.dtos.VerificationResultDTO;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import de.hbrs.se.rabbyte.service.AuthService;
import de.hbrs.se.rabbyte.util.Globals;
import de.hbrs.se.rabbyte.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

@Route("verification")
@PageTitle(Globals.PageTitle.ACTIVATE)
@Theme(value = Lumo.class)
public class VerificationView extends VerticalLayout implements BeforeEnterObserver {

    private VerticalLayout layout;

    Map<String , List<String>> params;

    private VerificationResultDTO verificationResultDTO;

    private String token;

    @Autowired
    private VerificationControl verificationControl;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
            params = event.getLocation().getQueryParameters().getParameters();
            token = params.get("token").get(0);

            if(!verificationControl.length(token)) {
                event.rerouteTo(LoginView.class);
            }

            VerificationCodeDTO verificationCodeDTO = verificationControl.getVerificationCode(token);
            if(verificationCodeDTO == null | verificationCodeDTO.getId() < 1  ) {
                event.rerouteTo(LoginView.class);
            }

    }

    public VerificationView() {

        layout = new VerticalLayout();

        H1 h1 = new H1();
        h1.setText("Aktiviere deinen Account");

        Label infoText = new Label("Um Ihr Konto zu aktivieren, klicken Sie bitte auf die unten stehende Taste");

        Button button = new Button();
        button.setText("Aktiviere Account");
        button.addClickListener( e -> {
            token = params.get("token").get(0);
            verificationResultDTO = verificationControl.activate(token);

            if(verificationResultDTO.getActivationResult()) {
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



