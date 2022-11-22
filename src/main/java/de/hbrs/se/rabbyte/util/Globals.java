package de.hbrs.se.rabbyte.util;

import com.vaadin.flow.component.combobox.ComboBox;

public class Globals {

    private Globals(){
        throw new IllegalStateException(IllegalState.MESSAGE_UTILS);
    }

    public static ComboBox<String> facultyComboBox(ComboBox<String> facultyComboBox) {

        facultyComboBox.setAllowCustomValue(false);
        facultyComboBox.setPlaceholder("Wähle Fachbereich");
        facultyComboBox.setItems("Angewandte Naturwissenschaften" , "Elektrotechnik, Maschinenbau & Technikjournalismus" ,
                "Informatik" , "Sozialpolitik und Soziale Sicherung" , "Wirtschaftswissenschaften" );
        return facultyComboBox;
    }



    public static class Path {


        private Path() { throw new IllegalStateException(IllegalState.MESSAGE_UTILS);}
        public static final String REGISTRATION_VIEW = "registration";
        public static final String COMMON_PASSWORD_LIST = "src/main/resources/commonPasswordList.txt";
    }

    public static class PageTitle {
        private PageTitle() { throw new IllegalStateException(IllegalState.MESSAGE_UTILS);}
        public static final String REGISTRATION_VIEW = "Registration";
        public static final String ACTIVATE = "Activation" ;
    }

    public static class Regex {
        private Regex() { throw new IllegalStateException(IllegalState.MESSAGE_UTILS);}
        public static final String BUSINESS_NAME = "^[a-zA-Z0-9& ]*{2,40}$";
        public static final String EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
                + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        public static final String LAST_NAME = "^([^\\p{N}\\p{S}\\p{C}\\p{P}]{2,20})$";
        public static final String FIRST_NAME = "^([^\\p{N}\\p{S}\\p{C}\\p{P}]{2,20})$";

    }

    public static class IllegalState {
        private IllegalState() {
            throw new IllegalStateException(IllegalState.MESSAGE_UTILS);
        }

        public static final String MESSAGE_UTILS = "IllegalStateException: Utility Class";
        public static final String MESSAGE_FACTORY = "IllegalStateException: Factory Class";
    }

    public static class Email {
        private Email() {
            throw new IllegalStateException(IllegalState.MESSAGE_UTILS);
        }

        public static final String HOST = "smtp.gmail.com";
        public static final int PORT = 587;
        public static final String GMAIL = "rplattform@gmail.com" ;
        public static final String APP_AUTHENTICATION = "duslwhnrymgvmdjd" ;

        public static final String TEXT_REGISTRATION = "To confirm your account, please click here:" + "\n" +
                "http://localhost:8080/verification?token=";
        public static final String SUBJECT_REGISTRATION = "Registration";
        public static final String EMAIL_SENDER = "rplattform@gmail.com";

    }

    public static class FieldErrorMessages {
        private FieldErrorMessages() {
            throw  new IllegalStateException(IllegalState.MESSAGE_UTILS);
        }

        public static final String MEMORIZED_SECRET_TOO_COMMON = "Ihr Passwort ist eines der häufigsten Passwörter. Bitte wählen sie ein anderes";
        public static final String DIFFERENT_PASSWORDS = "Unterschiedliche Passwörter";
        public static final String PASSWORD_TOO_SHORT = "Das Password muss mindestens 8 Zeichen sein";
        public static final String EMAIL_IN_USE = "Diese E-Mail wird bereits verwendet" ;
        public static final String INVALID_EMAIL = "Das Format der Email ist nicht gültig ";
        public static final String INVALID_LAST_NAME = "Ungültiger Vorname" ;
        public static final String INVALID_FIRST_NAME = "Ungültiger Nachname" ;
        public static final String BUSINESS_NAME = "Ungültiger Geschäftsname" ;
        public static final String BUSINESS_NAME_IN_USE = "Geschäftsname wird schon verwendet" ;
    }

}
