package de.hbrs.se.rabbyte.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class GlobalsTest {

    String globalsErrorMessage = "class de.hbrs.se.rabbyte.util.GlobalsTest cannot access a member of class de.hbrs.se.rabbyte.util.Globals with modifiers \"private\"";
    String globalsPathErrorMessage = "class de.hbrs.se.rabbyte.util.GlobalsTest cannot access a member of class de.hbrs.se.rabbyte.util.Globals$Path with modifiers \"private\"";
    String globalsPageTitleErrorMessage = "class de.hbrs.se.rabbyte.util.GlobalsTest cannot access a member of class de.hbrs.se.rabbyte.util.Globals$PageTitle with modifiers \"private\"";
    String globalsRegexErrorMessage = "class de.hbrs.se.rabbyte.util.GlobalsTest cannot access a member of class de.hbrs.se.rabbyte.util.Globals$Regex with modifiers \"private\"";
    String globalsStateExceptionErrorMessage = "class de.hbrs.se.rabbyte.util.GlobalsTest cannot access a member of class de.hbrs.se.rabbyte.util.Globals$StateException with modifiers \"private\"";

    @Test
    void itShouldThrowIllegalAccessExceptionWhenInstancingGlobals() throws NoSuchMethodException {
        Constructor<Globals> constructor = Globals.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(globalsErrorMessage, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class,constructor::newInstance);
    }

    @Test
    void facultyComboBox() {
    }

    @Test
    void throwIllegalAccessExceptionWhenInstancingGlobalsPath() throws NoSuchMethodException {
        Constructor<Globals.Path> constructor = Globals.Path.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(globalsPathErrorMessage, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class,constructor::newInstance);
    }
    @Test
    void throwIllegalAccessExceptionWhenInstancingGlobalsPageTitle() throws NoSuchMethodException {
        Constructor<Globals.PageTitle> constructor = Globals.PageTitle.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(globalsPageTitleErrorMessage, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class,constructor::newInstance);
    }
    @Test
    void throwIllegalAccessExceptionWhenInstancingGlobalsRegex() throws NoSuchMethodException {
        Constructor<Globals.Regex> constructor = Globals.Regex.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(globalsRegexErrorMessage, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class,constructor::newInstance);
    }

    @Test
    void throwIllegalAccessExceptionWhenInstancingGlobalsStateException() throws NoSuchMethodException {
        Constructor<Globals.StateException> constructor = Globals.StateException.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        Throwable exceptionThatWasThrown = assertThrows(IllegalAccessException.class, constructor::newInstance);
        assertEquals(globalsStateExceptionErrorMessage, exceptionThatWasThrown.getMessage());
        constructor.setAccessible(true);
        assertThrows(ReflectiveOperationException.class,constructor::newInstance);
    }
}