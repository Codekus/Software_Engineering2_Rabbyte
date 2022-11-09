package de.hbrs.se.rabbyte.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinSession;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.repository.GeneralUserRepository;
import de.hbrs.se.rabbyte.util.CryptographyUtil;
import de.hbrs.se.rabbyte.views.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class SecurityService  {

    @Autowired
    GeneralUserRepository generalUserRepository;

    private GeneralUserDTO generalUserDTO;

    public class AuthException extends Exception {

    }

    public void authenticate(String username, String password) throws AuthException, InvalidKeySpecException, NoSuchAlgorithmException {


        GeneralUserDTO user = generalUserRepository.findByEmail(username);
        String dbpassword = user.getPassword();
        String salt = user.getSalt();
                //generalUserRepository.findByEmailAndPassword(username, CryptographyUtil.encryptPassword(studentDTO.getPassword() , salt) );
        if (user == null || !Objects.equals(dbpassword, CryptographyUtil.encryptPassword(password, CryptographyUtil.fromHex(salt)))) {
            throw new AuthException();
        }

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(username, "[REDACTED]", Collections.emptyList());

        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
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
       // UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
       // SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
       // logoutHandler.logout(
       //         VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
       //         null);

        UI.getCurrent().getPage().setLocation("login");
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
    }
}