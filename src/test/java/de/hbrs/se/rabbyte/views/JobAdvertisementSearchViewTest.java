package de.hbrs.se.rabbyte.views;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class JobAdvertisementSearchViewTest {

    @Mock
    private JobAdvertisementSearchView jobAdvertisementSearchView;

    @Ignore
    public void firstEntryJobAdvertisementSearch(){
        Grid<JobAdvertisement> grid = jobAdvertisementSearchView.grid;
        JobAdvertisement firstJobAdvertisement = getFirstItem(grid);

        grid.asSingleSelect().setValue(firstJobAdvertisement);
        //not yet finished
        Assert.assertEquals(firstJobAdvertisement.getBusiness().getBusinessName(),"");
    }



    private JobAdvertisement getFirstItem(Grid<JobAdvertisement>grid){
       return ((ListDataProvider<JobAdvertisement>)grid.getDataProvider()).getItems().iterator().next();
    }


}
