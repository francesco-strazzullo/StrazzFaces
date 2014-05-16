package it.strazz.faces.extpanel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;

import org.primefaces.component.api.Widget;

@FacesComponent(value = ExtPanel.COMPONENT_TYPE)
@ResourceDependencies({
    @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
    @ResourceDependency(library = "primefaces", name = "primefaces.css"),
    @ResourceDependency(library = "primefaces", name = "primefaces.js"),
    @ResourceDependency(library = "strazzfaces", name = "extpanel.js"),
    @ResourceDependency(library = "css", name = "extpanel.css")
})
/**
 * @author f1l0
 */
public class ExtPanel extends UIInput implements Widget, ClientBehaviorHolder {
 
    public static final String COMPONENT_TYPE = "it.strazz.faces.ExtPanel";
    public static final String COMPONENT_FAMILY = "it.strazz.faces.components";

    private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("change"));
    static final Collection<String> AVAIABLE_POSITIONS = Collections.unmodifiableCollection(Arrays.asList("top","right","bottom","left"));
    static final String DEFAULT_POSITION = "top";

    @Override
    public Collection<String> getEventNames() {
        return EVENT_NAMES;
    }

    @Override
    public String getDefaultEventName() {
        return "change";
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public String getWidgetVar() {
        return (String) getStateHelper().eval(PropertyKeys.widgetVar, null);
    }

    public void setWidgetVar(String _widgetVar) {
        getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
    }

    public String getPosition() {
        return (String) getStateHelper().eval(PropertyKeys.position, DEFAULT_POSITION);
    }

    public void setPosition(String position) {
        getStateHelper().put(PropertyKeys.position, position);
    }

    public String getTitle() {
        return (String) getStateHelper().eval(PropertyKeys.title, null);
    }

    public void setTitle(String title) {
        getStateHelper().put(PropertyKeys.title, title);
    }

    public String getStyle() {
        return (String) getStateHelper().eval(PropertyKeys.style, null);
    }

    public void setStyle(String style) {
        getStateHelper().put(PropertyKeys.style, style);
    }

    public String getStyleClass() {
        return (String) getStateHelper().eval(PropertyKeys.styleClass, null);
    }

    public void setStyleClass(String styleClass) {
        getStateHelper().put(PropertyKeys.styleClass, styleClass);
    }

    public String getOnopen() {
        return (String) getStateHelper().eval(PropertyKeys.onopen, null);
    }

    public void setOnopen(String onopen) {
        getStateHelper().put(PropertyKeys.onopen, onopen);
    }

    public String getOnclose() {
        return (String) getStateHelper().eval(PropertyKeys.onclose, null);
    }

    public void setOnclose(String onclose) {
        getStateHelper().put(PropertyKeys.onclose, onclose);
    }

    @Override
    public String resolveWidgetVar() {
        FacesContext context = getFacesContext();
        String userWidgetVar = (String) getAttributes().get("widgetVar");

        if (userWidgetVar != null)
            return userWidgetVar;
        else return "widget_"+ getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
    }

    protected static enum PropertyKeys {
        title, position, style, styleClass, onopen, onclose, widgetVar;
    }
}
