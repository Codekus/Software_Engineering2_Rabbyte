package de.hbrs.se.rabbyte.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinServletRequest;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.repository.GeneralUserRepository;
import de.hbrs.se.rabbyte.views.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService  {

    @Autowired
    GeneralUserRepository generalUserRepository;

    public class AuthException extends Exception {

    }

    public void authenticate(String username, String password) throws AuthException {


        GeneralUserDTO user = generalUserRepository.findByEmailAndPassword(username, DigestUtils.sha256Hex(password) );
        if (user == null) {
            throw new AuthException();
        }
        createRoutes(user);
    }

    private void createRoutes(GeneralUserDTO user){
        getAuthorizedRoutes(user).stream().forEach(route -> RouteConfiguration.forSessionScope().setRoute(route.route, route.view, AppView.class));
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(GeneralUserDTO user){
        var routes = new ArrayList<AuthorizedRoute>();
        routes.add(new AuthorizedRoute("home", "Home", MainView.class));
        if (generalUserRepository.getStudent(user.getId()) != null){
            routes.add(new AuthorizedRoute("student", "Student", StudentUserView.class));
            routes.add(new AuthorizedRoute("search", "Search Job Advertisement", JobAdvertisementSearchView.class));
        } else if (generalUserRepository.getBusiness(user.getId()) != null) {
            routes.add(new AuthorizedRoute("create", "Create Job Advertisement", CreateJobAdvertisementView.class));

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