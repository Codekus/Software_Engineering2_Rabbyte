package de.hbrs.se.rabbyte.security;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.exception.AuthException;
import de.hbrs.se.rabbyte.repository.PersonRepository;
import de.hbrs.se.rabbyte.views.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.context.SecurityContextHolder;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@SpringBootTest
public class SecurityServiceTest {

    @Autowired
    SecurityService securityService;

    @Autowired
    PersonRepository personRepository;

    static String TEST_STUDENT_USERNAME;
    static String TEST_STUDENT2_USERNAME_NOT_ENABLED;
    static String TEST_BUSINESS_USERNAME;
    static String TEST_PASSWORD;

    Routes routes;

    @BeforeAll
    static void setUserCredentials() throws IOException {
        Properties prop = new Properties();
        FileInputStream f = null;
        try{
            f = new FileInputStream("src/test/ressources/test_credentials.properties");
            prop.load(f);
        } finally {
            assert f != null;
            f.close();
        }


        TEST_STUDENT_USERNAME = prop.getProperty("TEST_STUDENT_USERNAME");
        TEST_STUDENT2_USERNAME_NOT_ENABLED = prop.getProperty("TEST_STUDENT2_USERNAME_NOT_ENABLED");
        TEST_BUSINESS_USERNAME = prop.getProperty("TEST_BUSINESS_USERNAME");
        TEST_PASSWORD = prop.getProperty("TEST_PASSWORD");
    }

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        routes = new Routes().autoDiscoverViews("de.hbrs.se.rabbyte");
        MockVaadin.setup(routes);
    }

    @AfterEach
    void destroyAuthentication(){
        //SecurityContextHolder.getContext().setAuthentication(null);
    }

    @AfterAll
    static void tearDown(){
        MockVaadin.tearDown();
    }

    @Test
    void LogoutStudent_correctUserData_successfulLogout() throws InvalidKeySpecException, NoSuchAlgorithmException, AuthException {

        securityService.authenticate(TEST_STUDENT_USERNAME, TEST_PASSWORD);
        Assertions.assertTrue(SecurityUtils.isUserLoggedIn());
        securityService.logout();
        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());

    }


    @Test
    void authenticateStudent_correctUserData_successfulLogin() throws InvalidKeySpecException, NoSuchAlgorithmException, AuthException {

        PersonDTO student = personRepository.findByEmail(TEST_STUDENT_USERNAME);
        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());
        securityService.authenticate(TEST_STUDENT_USERNAME, TEST_PASSWORD);
        Assertions.assertEquals(student.getEmail(), securityService.getAuthenticatedUser().getEmail());
        Assertions.assertEquals(student.getId(), securityService.getAuthenticatedUserID());
        Assertions.assertTrue(SecurityUtils.isUserLoggedIn());
    }

    @Test
    void LogoutBusiness_correctUserData_successfulLogout() throws InvalidKeySpecException, NoSuchAlgorithmException, AuthException {

        securityService.authenticate(TEST_BUSINESS_USERNAME, TEST_PASSWORD);
        Assertions.assertTrue(SecurityUtils.isUserLoggedIn());
        securityService.logout();
        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());
    }


    @Test
    void authenticateBusiness_correctUserData_successfulLogin() throws InvalidKeySpecException, NoSuchAlgorithmException, AuthException {

        PersonDTO business = personRepository.findByEmail(TEST_BUSINESS_USERNAME);
        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());
        securityService.authenticate(TEST_BUSINESS_USERNAME, TEST_PASSWORD);
        Assertions.assertEquals(business.getEmail(), securityService.getAuthenticatedUser().getEmail());
        Assertions.assertEquals(business.getId(), securityService.getAuthenticatedUserID());
        Assertions.assertTrue(SecurityUtils.isUserLoggedIn());
    }

    @Test
    void authenticate_falseUserData_ThrowsAuthException() {

        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());

        AuthException e = Assertions.assertThrows(AuthException.class, () -> {
            securityService.authenticate("FalseName@falsemail.com", "SomePW");
        });
        Assertions.assertEquals("User does not exist", e.getMessage());

        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());
    }

    @Test
    void authenticate_invalidUserData_ThrowsAuthException() {

        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());

        AuthException e = Assertions.assertThrows(AuthException.class, () -> {
            securityService.authenticate("<> !?", "SomePW");
        });
        Assertions.assertEquals("Invalid username", e.getMessage());

        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());
    }

    @Test
    void authenticate_wrongPassword_ThrowsAuthException() {

        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());

        Assertions.assertThrows(AuthException.class, () -> {
            securityService.authenticate(TEST_STUDENT_USERNAME, "SomePW");
        });

        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());
    }

    @Test
    void authenticate_accountNotEnabled_ThrowsAuthException(){
        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());

        AuthException e = Assertions.assertThrows(AuthException.class, () -> {
            securityService.authenticate(TEST_STUDENT2_USERNAME_NOT_ENABLED, TEST_PASSWORD);
        });
        Assertions.assertEquals("Der Account ist noch nicht aktiviert", e.getMessage());

        Assertions.assertFalse(SecurityUtils.isUserLoggedIn());
    }
    @Test
    void getRole_studentUser_returnsStudent(){
        PersonDTO student = personRepository.getStudent(20000145);
        Assertions.assertEquals("Student", securityService.getRole(student));
    }

    @Test
    void getRole_businessUser_returnsBusiness(){
        PersonDTO student = personRepository.getBusiness(20000146);
        Assertions.assertEquals("Business", securityService.getRole(student));
    }

    @Test
    void getAuthenticatedUserRole_studentUser_returnsStudent() throws AuthException, InvalidKeySpecException, NoSuchAlgorithmException {
        securityService.authenticate(TEST_STUDENT_USERNAME, TEST_PASSWORD);
        Assertions.assertTrue(SecurityUtils.isUserLoggedIn());
        Assertions.assertEquals("Student", securityService.getAuthenticatedUserRole());
    }

    @Test
    void getAuthenticatedUserRole_businessUser_returnsBusiness() throws AuthException, InvalidKeySpecException, NoSuchAlgorithmException {
        securityService.authenticate(TEST_BUSINESS_USERNAME, TEST_PASSWORD);
        Assertions.assertTrue(SecurityUtils.isUserLoggedIn());
        Assertions.assertEquals("Business", securityService.getAuthenticatedUserRole());
    }

    @Test
    void getAuthorizedRoutes_studentUser_returnsStudentRoutes() {
        List<AuthorizedRoute> expectedRoutes = Arrays.asList(
                new AuthorizedRoute("appview", "AppView", AppView.class),
                new AuthorizedRoute("student", "Student", StudentUserView.class),
                new AuthorizedRoute("", "Search Job Advertisement", JobAdvertisementSearchView.class)
        );
        PersonDTO student = personRepository.findByEmail(TEST_STUDENT_USERNAME);
        List<AuthorizedRoute> studentRoutes = securityService.getAuthorizedRoutes(student);

        Assertions.assertTrue(expectedRoutes
                .stream()
                .allMatch(exp -> studentRoutes.stream()
                        .anyMatch(stu ->
                                exp.route.equals(stu.route) &&
                                exp.name.equals(stu.name) &&
                                exp.view.equals(stu.view))));
    }

    @Test
    void getAuthorizedRoutes_businessUser_returnsBusinessRoutes() {
        List<AuthorizedRoute> expectedRoutes = Arrays.asList(
                new AuthorizedRoute("appview", "AppView", AppView.class),
                new AuthorizedRoute("jobAd", "Create Job Advertisement", CreateJobAdvertisementView.class),
                new AuthorizedRoute("", "Business", BusinessView.class)
        );
        PersonDTO business = personRepository.findByEmail(TEST_BUSINESS_USERNAME);
        List<AuthorizedRoute> businessRoutes = securityService.getAuthorizedRoutes(business);

        Assertions.assertTrue(expectedRoutes
                .stream()
                .allMatch(exp -> businessRoutes.stream()
                        .anyMatch(bus ->
                                exp.route.equals(bus.route) &&
                                        exp.name.equals(bus.name) &&
                                        exp.view.equals(bus.view))));
    }


}
