package de.hbrs.se.rabbyte.security;

import com.vaadin.flow.component.Component;

public class AuthorizedRoute {
    public String route;
    public String name;
    public Class <? extends Component> view;


    public AuthorizedRoute(String route, String name, Class <? extends Component> view){
        this.route = route;
        this.name = name;
        this.view = view;
    }
}
