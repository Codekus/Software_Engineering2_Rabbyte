package de.hbrs.se.rabbyte.util;

import java.util.ArrayList;
import java.util.List;

public class Globals {

    private Globals(){
        throw new IllegalStateException("Utility Class");
    }

    public static class Path {
        private Path() { throw new IllegalStateException("Utility");}
        public static final String REGISTRATION_VIEW = "registration/";
    }

    public static class PageTitle {
        private PageTitle() { throw new IllegalStateException("Utility");}
        public static final String REGISTRATION_VIEW = "Registration";
    }

}
