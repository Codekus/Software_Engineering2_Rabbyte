package selenium.Utils;

;

import javax.mail.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import java.util.Properties;


public class OutlookMail {

    public static List<Message> getInboxMessages() throws MessagingException, IOException {

        Properties prop = new Properties();
        FileInputStream f = null;
        try{
            f = new FileInputStream("src/test/ressources/test_credentials.properties");
            prop.load(f);
        } finally {
            assert f != null;
            f.close();
        }

        String mail = prop.getProperty("TEST_SELENIUM_MAIL");
        String pw = prop.getProperty("TEST_SELENIUM_PASSWORD");

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "outlook.office365.com");
        props.put("mail.imaps.port", "993");


        Session session = Session.getInstance(props);
        Store store = session.getStore();
        store.connect(mail, pw);


        Folder inbox = store.getFolder("Rabbyte");
        inbox.open(Folder.READ_ONLY);


        Message[] messages = inbox.getMessages();
        return List.of(messages);
    }

    public static String getLatestEmail() throws MessagingException, IOException {
        List<Message> messages2 = getInboxMessages();
        return messages2.get(messages2.size()-1).getContent().toString();
    }

}
