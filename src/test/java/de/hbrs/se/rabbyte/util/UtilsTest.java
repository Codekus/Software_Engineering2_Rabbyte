package de.hbrs.se.rabbyte.util;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import org.testng.annotations.Test;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    private static final String EXCEPTION_MESSAGE = "class de.hbrs.se.rabbyte.util.UtilsTest cannot access a member of class de.hbrs.se.rabbyte.util.Utils with modifiers \"private\"";

    @Test
    void appendLength() {
        Integer[] numberArray = {1, 2, 3};
        Integer[] numberArrayAppended = Utils.append(numberArray, 4);
        assertEquals(numberArray.length + 1, numberArrayAppended.length);
    }


    @Test
    void appendCheckObjectAppended() {

        Integer[] numberArray = {1, 2, 3};
        Integer[] numberArrayAppended = Utils.append(numberArray, 4);
        assertEquals(4, numberArrayAppended[numberArrayAppended.length - 1]);

    }


    @Test
    void triggerDialogMessage() {

        UI ui = new UI();
        UI.setCurrent(ui);

        VaadinSession session = Mockito.mock(VaadinSession.class);
        Mockito.when(session.hasLock()).thenReturn(true);
        ui.getInternals().setSession(session);
        Utils.triggerDialogMessage("headerText" , "message");
        UI.setCurrent(null);
    }

    @Test
    void throwIllegalAccesExceptionWhenInstancingUtils() throws NoSuchMethodException {
        Constructor<Utils> constructor = Utils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(EXCEPTION_MESSAGE, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class,constructor::newInstance);

    }
}