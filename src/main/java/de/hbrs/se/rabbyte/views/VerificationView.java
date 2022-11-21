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
import de.hbrs.se.rabbyte.control.factory.VerificationFactory;
import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.dtos.VerificationResultDTO;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import de.hbrs.se.rabbyte.util.EmailSenderService;
import de.hbrs.se.rabbyte.util.Globals;
import de.hbrs.se.rabbyte.util.NavigationUtil;
import de.hbrs.se.rabbyte.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Route("verification")
@PageTitle(Globals.PageTitle.ACTIVATE)
@Theme(value = Lumo.class)
public class VerificationView extends VerticalLayout implements BeforeEnterObserver {

    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationView.class.getName());

    private VerificationResultDTO verificationResultDTO;
    Map<String , List<String>> params;
    private String token;
    private String wholeToken;

    private VerticalLayout layout;
    H1 h1;
    Label infoText;
    Button button = new Button();

    EmailSenderService emailSenderService;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    VerificationControl verificationControl;

    private VerificationCodeDTO verificationCodeDTO;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        params = event.getLocation().getQueryParameters().getParameters();
        wholeToken = params.toString();

        //Check whole URL
        validateURL(event);

        try {
            if(params != null){
                //Checking token
                validVerificationToken(event);
            } else {
                NavigationUtil.toLoginView();
            }
        } catch (NullPointerException exception) {
            NavigationUtil.toLoginView();
        }

        try
        {
                //get DTO
                verificationCodeDTO = verificationControl.getVerificationCode(token);
                findVerificationCodeInDb(event);

                //Depending on date load either activate or resend
                if(verificationDateExpired(verificationCodeDTO)) {
                    this.add(verificationResend(verificationCodeDTO));
                } else {
                    this.add(verificationActivation());
                }

        } catch(Exception exception) {
            LOGGER.info("INFO: {}" ,  exception.getMessage());
        }

    }

    private boolean verificationDateExpired(VerificationCodeDTO verificationCodeDTO) {
        long time = Duration.between(verificationCodeDTO.getDate() , LocalDateTime.now()).toHours();
        return (time > 48);
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

    public VerticalLayout verificationActivation() {

        layout = new VerticalLayout();

        h1 = new H1();
        h1.setText("Aktiviere deinen Account");

        infoText = new Label("Um Ihr Konto zu aktivieren, klicken Sie bitte auf die unten stehende Taste");

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



        return layout;

    }

    public VerticalLayout verificationResend(VerificationCodeDTO verificationCodeDTO) {

        layout = new VerticalLayout();
        h1 = new H1();
        h1.setText("Neu sendens des Aktivierungscode");

        infoText = new Label("Ihr Aktivierungscode ist älter als 48 Stunden. Neu senden");

        button = new Button();
        button.addClickListener( e -> {
            VerificationCode verificationCode = VerificationFactory.updateVerificationCode(verificationCodeDTO );
            verificationCodeRepository.save(verificationCode);

            emailSenderService = new EmailSenderService(verificationCode);
            emailSenderService.sendEmail();

        });



        layout.add(h1 , infoText , button);
        layout.setPadding(true);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setAlignSelf(FlexComponent.Alignment.CENTER);

        return layout;
    }



    public VerificationView() {

    }

}





