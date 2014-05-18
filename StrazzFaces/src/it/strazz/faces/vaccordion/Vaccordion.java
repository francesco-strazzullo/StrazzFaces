package it.strazz.faces.vaccordion;


import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import org.primefaces.component.api.Widget;

@FacesComponent(value = Vaccordion.COMPONENT_TYPE)
@ResourceDependencies({
    @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
    @ResourceDependency(library = "primefaces", name = "primefaces.css"),
    @ResourceDependency(library = "primefaces", name = "primefaces.js"),
    @ResourceDependency(library = "css", name = "vaccordion.css"),
    @ResourceDependency(library = "strazzfaces", name = "vaccordion.js")
})
/**
 * @author f1l0
 */
public class Vaccordion extends UIComponentBase implements Widget {
 
    public static final String COMPONENT_TYPE = "it.strazz.faces.Vaccordion";
    public static final String COMPONENT_FAMILY = "it.strazz.faces.components";

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    // Header
    public String getHeader() {
        return (String) getStateHelper().eval(Vaccordion.PropertyKeys.header, null);
    }

    public void setHeader(String header) {
        getStateHelper().put(Vaccordion.PropertyKeys.header, header);
    }

    // Show Header
    public boolean isShowHeader() {
        return (boolean) getStateHelper().eval(Vaccordion.PropertyKeys.showHeader, true);
    }

    public void setShowHeader(boolean showHeader) {
        getStateHelper().put(Vaccordion.PropertyKeys.showHeader, showHeader);
    }

    // Footer
    public String getFooter() {
        return (String) getStateHelper().eval(Vaccordion.PropertyKeys.footer, null);
    }

    public void setFooter(String footer) {
        getStateHelper().put(Vaccordion.PropertyKeys.footer, footer);
    }

    // Inline style
    public String getStyle() {
        return (String) getStateHelper().eval(Vaccordion.PropertyKeys.style, null);
    }

    public void setStyle(String style) {
        getStateHelper().put(Vaccordion.PropertyKeys.style, style);
    }

    // CSS styleClass for Vertical Accordion
    public String getStyleClass() {
        return (String) getStateHelper().eval(Vaccordion.PropertyKeys.styleClass, null);
    }

    public void setStyleClass(String styleClass) {
        getStateHelper().put(Vaccordion.PropertyKeys.styleClass, styleClass);
    }

    // CSS styleClass for opened tab
    public String getStyleClassOpen() {
        return (String) getStateHelper().eval(Vaccordion.PropertyKeys.styleClassOpen, null);
    }

    public void setStyleClassOpen(String styleClassOpen) {
        getStateHelper().put(Vaccordion.PropertyKeys.styleClassOpen, styleClassOpen);
    }

    // CSS styleClass for closed tab
    public String getStyleClassClose() {
        return (String) getStateHelper().eval(Vaccordion.PropertyKeys.styleClassClose, null);
    }

    public void setStyleClassClose(String styleClassClose) {
        getStateHelper().put(Vaccordion.PropertyKeys.styleClassClose, styleClassClose);
    }

    // Icon for opened tab
    public String getIconOpen() {
        return (String) getStateHelper().eval(Vaccordion.PropertyKeys.iconOpen, "ui-icon-bullet");
    }

    public void setIconOpen(String iconOpen) {
        getStateHelper().put(Vaccordion.PropertyKeys.iconOpen, iconOpen);
    }

    // Icon for closed tab
    public String getIconClose() {
        return (String) getStateHelper().eval(Vaccordion.PropertyKeys.iconClose, "ui-icon-radio-on");
    }

    public void setIconClose(String iconClose) {
        getStateHelper().put(Vaccordion.PropertyKeys.iconClose, iconClose);
    }

    // Icon position. Default is "middle"
    public String getIconPos() {
        return (String) getStateHelper().eval(Vaccordion.PropertyKeys.iconPos, "middle");
    }

    public void setIconPos(String iconPos) {
        getStateHelper().put(Vaccordion.PropertyKeys.iconPos, iconPos);
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
        header, showHeader, footer, iconOpen, iconClose, iconPos, style, styleClass, styleClassOpen, styleClassClose;
    }
}
