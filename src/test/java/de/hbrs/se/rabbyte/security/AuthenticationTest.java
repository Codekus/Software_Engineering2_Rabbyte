package de.hbrs.se.rabbyte.security;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.exception.AuthException;
import de.hbrs.se.rabbyte.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@SpringBootTest
public class AuthenticationTest {

    @Autowired
    SecurityService securityService;

    @Autowired
    PersonRepository personRepository;

    final static String TEST_STUDENT_USERNAME = "UnitStudentTest@Test.de";
    final static String TEST_BUSINESS_USERNAME = "UnitBusinessTest@Test.de";
    final static String TEST_PASSWORD = "test123321";

    Routes routes;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        routes = new Routes().autoDiscoverViews("de.hbrs.se.rabbyte");
        MockVaadin.setup(routes);
    }

    @AfterEach
    void destroyAuthentication(){
        SecurityContextHolder.getContext().setAuthentication(null);
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
}
