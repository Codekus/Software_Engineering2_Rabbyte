package de.hbrs.se.rabbyte.util;

import com.vaadin.flow.component.combobox.ComboBox;

public class Globals {

    private Globals(){
        throw new IllegalStateException("Utility Class");
    }

    public static ComboBox<String> facultyComboBox(ComboBox<String> facultyComboBox) {

        facultyComboBox.setAllowCustomValue(false);
        facultyComboBox.setPlaceholder("Wähle Fachbereich");
        facultyComboBox.setItems("Angewandte Naturwissenschaften" , "Elektrotechnik, Maschinenbau & Technikjournalismus" ,
                "Informatik" , "Sozialpolitik und Soziale Sicherung" , "Wirtschaftswissenschaften" );
        return facultyComboBox;
    }



    public static class Path {
        private Path() { throw new IllegalStateException("Utility");}
        public static final String REGISTRATION_VIEW = "registration/";
    }

    public static class PageTitle {
        private PageTitle() { throw new IllegalStateException("Utility");}
        public static final String REGISTRATION_VIEW = "Registration";
    }

    public static class Regex {
        private Regex() { throw new IllegalStateException("Utility");}
        public static final String BUSINESS_NAME = "^[a-zA-Z0-9& ]*{2,40}$";
        public static final String EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
                + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        public static final String LAST_NAME = "^([^\\p{N}\\p{S}\\p{C}\\p{P}]{2,20})$";
        public static final String FIRST_NAME = "^([^\\p{N}\\p{S}\\p{C}\\p{P}]{2,20})$";

    }

}
