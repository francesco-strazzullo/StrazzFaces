package it.strazz.faces.vaccordion;


import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

@FacesComponent(value = Vtab.COMPONENT_TYPE)
/**
 * @author f1l0
 */
public class Vtab extends UIComponentBase {
 
    public static final String COMPONENT_TYPE = "it.strazz.faces.Vtab";
    public static final String COMPONENT_FAMILY = "it.strazz.faces.components";

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    // Inline style
    public String getStyle() {
        return (String) getStateHelper().eval(Vtab.PropertyKeys.style, null);
    }

    public void setStyle(String style) {
        getStateHelper().put(Vtab.PropertyKeys.style, style);
    }

    // CSS styleClass for opened tab
    public String getStyleClass() {
        return (String) getStateHelper().eval(Vtab.PropertyKeys.styleClass, null);
    }

    public void setStyleClass(String styleClass) {
        getStateHelper().put(Vtab.PropertyKeys.styleClass, styleClass);
    }

    protected static enum PropertyKeys {
        style, styleClass;
    }
}
