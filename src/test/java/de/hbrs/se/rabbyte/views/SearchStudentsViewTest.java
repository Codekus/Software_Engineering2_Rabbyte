package de.hbrs.se.rabbyte.views;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;






@SpringBootTest
public class SearchStudentsViewTest extends AbstractTestNGSpringContextTests {

    private SearchStudentsView searchStudentsView;

    @BeforeMethod
    void setup(){
        this.searchStudentsView = applicationContext.getBean(SearchStudentsView.class);
    }


    @Test
    @DisplayName("Search-Students-Test")
    public void searchStudentsViewTest() {
        searchStudentsView.searchField.setValue("äkl.-");

        Assert.assertEquals("Keine Übereinstimmungen!", searchStudentsView.infoMessage.getText());

        searchStudentsView.searchField.setValue("max@gmx.de");

        long resArray = searchStudentsView.grid.getColumns().stream().count();

        Assert.assertEquals(5, resArray);
    }


}
