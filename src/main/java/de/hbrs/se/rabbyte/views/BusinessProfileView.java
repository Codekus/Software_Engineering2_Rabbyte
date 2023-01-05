package de.hbrs.se.rabbyte.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "business-profile")
@PageTitle("Unternehmensprofil")
public class BusinessProfileView extends VerticalLayout implements HasUrlParameter<String>, AfterNavigationObserver {

    @Autowired
    CrmService service;
    BusinessDTO requestedBusinessProfile;

    public BusinessProfileView() {
        addClassName("business-profile-view");
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.valueOf("CENTER"));
    }

    @Override
    public void setParameter(BeforeEvent event, String paramRequestedBusinessId) {
        BusinessDTO business;
        try {
            Integer id = Integer.parseInt(paramRequestedBusinessId);
            business = service.findBusinessById(id);
        } catch(NumberFormatException e) {
            business = null;
        }
        if(business == null) {
            // Die requested BusinessId existiert nicht! => reroute to main
            event.forwardTo("");
        }
        this.requestedBusinessProfile = business;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        // setze unternehmensnamen als titel
        add(new H3(requestedBusinessProfile.getBusinessName()));

        // setze unternehmensprofilbild, zurzeit ein placeholder
        Image image = new Image("generic_businesslogo.svg", "Unternehmenslogo konnte nicht geladen werden!");
        image.setWidth("300");
        add(image);

        // zeige unternehmensadresse
        Span plz = new Span(String.valueOf(requestedBusinessProfile.getPlz()));
        Span stadt = new Span("k.A.");
        Span land = new Span("k.A.");
        Span strasse = new Span("k.A.");
        Span hausnummer = new Span("k.A.");

        if( requestedBusinessProfile.getCity() != null )
            stadt = new Span(requestedBusinessProfile.getCity());
        if( requestedBusinessProfile.getCountry() != null )
            land = new Span(requestedBusinessProfile.getCountry());
        if( requestedBusinessProfile.getStreet() != null )
            strasse = new Span(requestedBusinessProfile.getStreet());
        if( requestedBusinessProfile.getStreetNumber() != null)
            hausnummer = new Span(requestedBusinessProfile.getStreetNumber());

        HorizontalLayout horizontalLayoutAdresse = new HorizontalLayout();
        VerticalLayout verticalLayoutLeftSide = new VerticalLayout();
        VerticalLayout verticalLayoutRightSide = new VerticalLayout();
        verticalLayoutLeftSide.add(new Span("PLZ: "), new Span("Stadt: "), new Span("Land: "), new Span("Strasse: "), new Span("Hausnummer: "));
        verticalLayoutRightSide.add(plz, stadt, land, strasse, hausnummer);
        horizontalLayoutAdresse.add(verticalLayoutLeftSide, verticalLayoutRightSide);
        add(horizontalLayoutAdresse);

    }
}
