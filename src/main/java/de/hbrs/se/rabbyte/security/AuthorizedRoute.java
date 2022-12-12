package de.hbrs.se.rabbyte.security;

import com.vaadin.flow.component.Component;

public class AuthorizedRoute {
    String route;
    String name;
    Class <? extends Component> view;


    public AuthorizedRoute(String route, String name, Class <? extends Component> view){
        this.route = route;
        this.name = name;
        this.view = view;
    }
}
