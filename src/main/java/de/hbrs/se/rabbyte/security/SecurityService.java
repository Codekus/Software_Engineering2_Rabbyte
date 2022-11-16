package de.hbrs.se.rabbyte.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinSession;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.exception.AuthException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class SecurityService  {

    private static final String LOGOUT_SUCCESS_URL = "/";
    private static final String SETTINGS_URL = "settings";
    @Autowired
    GeneralUserRepository generalUserRepository;

    public void authenticate(String username, String password) throws AuthException, InvalidKeySpecException, NoSuchAlgorithmException {

        //Erzeuge einen Regulären Ausdruck zum prüfen von wohlgeformtheit
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(username);

        // Prüfe ob Username wohlgeformt ist, ansonsten wird eine Exception geworfen die in der LoginView behandelt wird
        if(!matcher.find()) {
            throw new AuthException("Invalid username");
        }

        /* Erzeuge UserDTO durch suchen mittels Email in der Datenbank
           Falls die Email nicht in der Datenbank vorhanden ist, wird der UserDTO auf NULL gesetzt, ansonsten wird ein gültiger UserDTO erzeugt
         */
        GeneralUserDTO user = generalUserRepository.findByEmail(username);

        /* Prüfe ob der User mit der angegebenen Email überhaupt in der Datenbank existiert
           Falls der User nicht existiert wird eine Exeption geworfen die in der LoginView behandelt wird
        */
        if(user == null) {
            throw new AuthException("User does not exist");
        }

        // Hole das gehashte Passwort aus der Datenbank die dem UserDTO zugehörig ist
        String dbpassword = user.getPassword();

        // Hole den Salt-Wert aus der Datenbank die dem UserDTO zugehörig ist
        String salt = user.getSalt();

        /* Prüfe ob das Passwort aus der Datenbank gleich ist zu dem gehashten Wert, der aus der Eingabe und dem Salt von der Datenbank besteht, ist
           Falls die Passwörter nicht übereinstimmen wird eine Exception geworfen die in der LoginView behandelt wird
        */
        if (!Objects.equals(dbpassword, CryptographyUtil.encryptPassword(password, CryptographyUtil.fromHex(salt)))) {
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
        getAuthorizedRoutes(user).stream().forEach(route ->{
            //To set a Route we need both the path and the view to be unused, so we check for both and remove both before adding them.
            if(RouteConfiguration.forSessionScope().isRouteRegistered(route.view)){
                RouteConfiguration.forSessionScope().removeRoute(route.route);
                if(RouteConfiguration.forSessionScope().isPathRegistered(route.route)){
                    RouteConfiguration.forSessionScope().removeRoute(route.route);
                }
                //RouteConfiguration.forSessionScope().setRoute(route.route, route.view);
                RouteConfiguration.forSessionScope().setRoute(route.route, route.view, AppView.class);
            }else{
                //RouteConfiguration.forSessionScope().setRoute(route.route, route.view);
                RouteConfiguration.forSessionScope().setRoute(route.route, route.view, AppView.class);
            }

        });
    }


    public String getRole(GeneralUserDTO user){
        if (generalUserRepository.getStudent(user.getId()) != null){
            return "Student";
        }
        else if (generalUserRepository.getBusiness(user.getId()) != null){
            return "Business";
        }
        else{
            return "None";
        }
    }
    public List<AuthorizedRoute> getAuthorizedRoutes(GeneralUserDTO user){
        var routes = new ArrayList<AuthorizedRoute>();
        routes.add(new AuthorizedRoute("appview", "AppView", AppView.class));
        if (Objects.equals(getRole(user), "Student")){
            routes.add(new AuthorizedRoute("student", "Student", StudentUserView.class));
            routes.add(new AuthorizedRoute("main", "Search Job Advertisement", JobAdvertisementSearchView.class));
        } else if (Objects.equals(getRole(user), "Business")) {
            routes.add(new AuthorizedRoute("main", "Create Job Advertisement", CreateJobAdvertisementView.class));

        }
        return routes;
    }

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

        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(
                VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                null);
        SecurityContextHolder.getContext().setAuthentication(null);

    }
    public void settings() {
        UI.getCurrent().getPage().setLocation(SETTINGS_URL);
    }
}