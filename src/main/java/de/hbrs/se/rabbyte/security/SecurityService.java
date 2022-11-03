package de.hbrs.se.rabbyte.security;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.entities.Role;
import de.hbrs.se.rabbyte.repository.GeneralUserRepository;
import de.hbrs.se.rabbyte.views.MainView;
import de.hbrs.se.rabbyte.views.StudentUserView;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import de.hbrs.se.rabbyte.entities.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService  {

    @Autowired
    GeneralUserRepository generalUserRepository;

    public class AuthException extends Exception {

    }

    public record AuthorizedRoute(String route, String name, Class <? extends Component> view){

    }

    public void authenticate(String username, String password) throws AuthException {


        GeneralUserDTO user = generalUserRepository.findByEmailAndPassword(username, DigestUtils.sha256Hex(password) );
        if (user == null) {
            throw new AuthException();
        }
        createRoutes(user.getRoles());
    }

    private void createRoutes(Role role){

    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Role role){
        var routes = new ArrayList<AuthorizedRoute>();
        routes.add(new AuthorizedRoute("home", "Home", MainView.class));
        if (role.equals(Role.STUDENT)){
            routes.add(new AuthorizedRoute("student", "Student", StudentUserView.class));
        } else if (role.equals(Role.BUSINESS)) {
            
        } else if (role.equals(Role.ADMIN)) {
            
        }
        return routes;
    }

    private static final String LOGOUT_SUCCESS_URL = "/";

    public UserDetails getAuthenticatedUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserDetails) context.getAuthentication().getPrincipal();
        }
        // Anonymous or no authentication.
        return null;
    }

    public void logout() {
        UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(
                VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                null);
    }
}