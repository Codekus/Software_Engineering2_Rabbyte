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

}
