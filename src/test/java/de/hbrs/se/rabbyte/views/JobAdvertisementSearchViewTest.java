package de.hbrs.se.rabbyte.views;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JobAdvertisementSearchViewTest {

    @Autowired
    private JobAdvertisementSearchView jobAdvertisementSearchView;


    @Test
    @DisplayName("Job-Advertisement-Search-Test")
    public void jobAdvertisementSearchViewTest() {
        jobAdvertisementSearchView.searchField.setValue("test123");

        Assert.assertEquals("Keine Übereinstimmungen!", jobAdvertisementSearchView.infoMessage.getText());

        jobAdvertisementSearchView.searchField.setValue("test");

        long resArray = jobAdvertisementSearchView.grid.getColumns().stream().count();

        Assert.assertEquals(4, resArray);
    }


}
