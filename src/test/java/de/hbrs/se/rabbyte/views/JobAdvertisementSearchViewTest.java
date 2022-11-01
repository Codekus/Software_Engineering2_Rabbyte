package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import org.junit.Assert;
import org.junit.Test;
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
    public void firstEntryJobAdvertisementSearch(){
        Grid<JobAdvertisement> grid = jobAdvertisementSearchView.grid;
        JobAdvertisement firstJobAdvertisement = getFirstItem(grid);

        grid.asSingleSelect().setValue(firstJobAdvertisement);

        //not yet finished
        Assert.assertEquals(firstJobAdvertisement.getBusiness(),"");
    }

    private JobAdvertisement getFirstItem(Grid<JobAdvertisement>grid){
       return ((ListDataProvider<JobAdvertisement>)grid.getDataProvider()).getItems().iterator().next();
    }
}
