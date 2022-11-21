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
import de.hbrs.se.rabbyte.util.Globals;
import de.hbrs.se.rabbyte.util.NavigationUtil;
import de.hbrs.se.rabbyte.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Route("verification")
@PageTitle(Globals.PageTitle.ACTIVATE)
@Theme(value = Lumo.class)
public class VerificationView extends VerticalLayout implements BeforeEnterObserver {

    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationView.class.getName());

    private VerticalLayout layout;

    Map<String , List<String>> params;

    private VerificationResultDTO verificationResultDTO;

    private String token;

    private String wholeToken;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    VerificationControl verificationControl;

    private VerificationCodeDTO verificationCodeDTO;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        params = event.getLocation().getQueryParameters().getParameters();
        wholeToken = params.toString();

        validateURL(event);

        //test = params.get("t").get(0);

        //Utils.triggerDialogMessage(test ,test);

        try {
            if(params != null){
                validVerificationToken(event);
            } else {
                NavigationUtil.toLoginView();
            }
        } catch (NullPointerException exception) {
            NavigationUtil.toLoginView();
        }

        verificationActivation();

        try
        {
            verificationCodeDTO = verificationControl.getVerificationCode(token);

            findVerificationCodeInDb(event);


        } catch(Exception exception) {
            LOGGER.info("INFO: {}" ,  exception.getMessage());
        }
    }

    private void validateURL(BeforeEnterEvent event) {
        try {
            if(wholeToken.length() != 46) {
                event.rerouteTo(LoginView.class);
            }
        } catch (Exception exception) {
            LOGGER.info("INFO: {}" ,  exception.getMessage());
        }

    }


    private void findVerificationCodeInDb(BeforeEnterEvent event) {
        if(verificationCodeDTO == null) {
            event.rerouteTo(LoginView.class);
       }
    }

    private void validVerificationToken(BeforeEnterEvent event) {
        try {
            token = params.get("token").get(0);
        } catch (Exception exception) {
            event.rerouteTo(LoginView.class);
        }

        if(!verificationControl.length(token)) {
            NavigationUtil.toLoginView();
        }
        if(verificationControl.getVerificationCode(token) == null  ) {
            NavigationUtil.toLoginView();
        }
    }

    public VerificationCodeDTO createVerificationDTO(String token) {

    return verificationCodeRepository.findVerificationCodeByToken(token);
    }

    public VerticalLayout verificationActivation() {

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
                Utils.triggerDialogMessage("Ihr Account ist jetzt aktiv!" , "Sie können sich jetzt einloggen");
                NavigationUtil.toLoginView();

            } else {
                Utils.triggerDialogMessage("Failure!" , ":(");
            }


        });

        layout.add(h1 , infoText , button);
        layout.setPadding(true);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setAlignSelf(FlexComponent.Alignment.CENTER);

        this.add(layout);

        return layout;

    }



    public VerificationView() {

    }

}





