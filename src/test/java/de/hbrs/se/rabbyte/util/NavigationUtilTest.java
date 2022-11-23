package de.hbrs.se.rabbyte.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class NavigationUtilTest {

    String errorMessage = "class org.hbrs.se2.project.coll.util.UtilNavigationTest cannot access a member of class org.hbrs.se2.project.coll.util.UtilNavigation with modifiers \"private\"";

    @Test
    void itShouldThrowIllegalAccessExceptionWhenInstancing() throws NoSuchMethodException {
        Constructor<NavigationUtil> constructor = NavigationUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(errorMessage, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class,constructor::newInstance);
    }

}