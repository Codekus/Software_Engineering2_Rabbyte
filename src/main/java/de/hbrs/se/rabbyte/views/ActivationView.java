package de.hbrs.se.rabbyte.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import de.hbrs.se.rabbyte.dtos.VerificationCodeDTO;
import de.hbrs.se.rabbyte.entities.VerificationCode;
import de.hbrs.se.rabbyte.repository.GeneralUserRepository;
import de.hbrs.se.rabbyte.repository.VerificationCodeRepository;
import de.hbrs.se.rabbyte.service.AuthService;
import de.hbrs.se.rabbyte.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

@Route("activate")
public class ActivationView extends VerticalLayout implements BeforeEnterObserver {

    private VerticalLayout layout;

    private AuthService authService;

    Map<String , List<String>> params;

    @Autowired
    VerificationCodeRepository verificationCodeRepository;

    @Autowired
    GeneralUserRepository generalUserRepository;

    public ActivationView( AuthService authService) {
        this.authService = authService;
    }
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
            params = event.getLocation().getQueryParameters().getParameters();



    }

    public ActivationView() {

        layout = new VerticalLayout();

        Button button = new Button();

        button.addClickListener( e -> {
            String token = params.get("token").get(0);

            Utils.triggerDialogMessage(token ,  "ww");
            VerificationCodeDTO verificationCodeDTO;
            verificationCodeDTO = verificationCodeRepository.findByToken(token);
            Utils.triggerDialogMessage("Not yet", "2w");
            if(verificationCodeDTO.getId() >0 & verificationCodeDTO != null) {
                Utils.triggerDialogMessage(String.valueOf(verificationCodeDTO.getId()), "2w");
            }   else {
                Utils.triggerDialogMessage(("ERROR"), "2w");
            }
        });

        layout.add(button);
        add(layout);
        }
    }



