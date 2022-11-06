package de.hbrs.se.rabbyte.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    private final String exceptionMessage = "class de.hbrs.se.rabbyte.util.UtilsTest cannot access a member of class de.hbrs.se.rabbyte.util.Utils with modifiers \"private\"";
    @Test
    void append() {
    }

    @Test
    void triggerDialogMessage() {
    }

    @Test
    void ThrowIllegalAccesExceptionWhenInstancingUtils() throws NoSuchMethodException {
        Constructor<Utils> constructor = Utils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(exceptionMessage, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class,constructor::newInstance);

    }
}