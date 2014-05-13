package it.strazz.faces.countdown;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import static javax.swing.border.TitledBorder.DEFAULT_POSITION;

import org.primefaces.component.api.Widget;

@FacesComponent(value = CountDown.COMPONENT_TYPE)
@ResourceDependencies({
    @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
    @ResourceDependency(library = "primefaces", name = "primefaces.css"),
    @ResourceDependency(library = "primefaces", name = "primefaces.js"),
    @ResourceDependency(library = "strazzfaces", name = "countdown.js"),
})
/**
 * @author f1l0
 */
public class CountDown extends UIInput implements Widget, ClientBehaviorHolder {
 
	public static final String COMPONENT_TYPE = "it.strazz.faces.CountDown";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";

	static final Collection<String> AVAIABLE_SIZE = Collections.unmodifiableCollection(Arrays.asList("lg","md","sm","xs"));
	static final String DEFAULT_SIZE = "md";

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
	
	public String getSize() {
            return (String) getStateHelper().eval(PropertyKeys.size, DEFAULT_SIZE);
	}

	public void setSize(String size) {
            getStateHelper().put(PropertyKeys.size, size);
	}
	
        public Date getDate() {
            return (Date) getStateHelper().eval(PropertyKeys.date, null);
	}

	public void setDate(Date date) {
            getStateHelper().put(PropertyKeys.date, date);
	}

        public boolean isAm() {
            return (boolean) getStateHelper().eval(PropertyKeys.am, null);
	}

	public void setAm(boolean am) {
            getStateHelper().put(PropertyKeys.date, am);
	}

        public boolean isShowHour() {
            return (boolean) getStateHelper().eval(PropertyKeys.showHour, true);
	}

	public void setShowHour(boolean showHour) {
            getStateHelper().put(PropertyKeys.showHour, showHour);
	}

        public boolean isShowMinute() {
            return (boolean) getStateHelper().eval(PropertyKeys.showMinute, true);
	}

	public void setShowMinute(boolean showMinute) {
            getStateHelper().put(PropertyKeys.showMinute, showMinute);
	}

        public boolean isShowSecond() {
            return (boolean) getStateHelper().eval(PropertyKeys.showSecond, true);
	}

	public void setShowSecond(boolean showSecond) {
            getStateHelper().put(PropertyKeys.showSecond, showSecond);
	}

        public int getTzoneOffset() {
            return (int) getStateHelper().eval(PropertyKeys.showSecond, 0);
	}

	public void setTzoneOffset(int offset) {
            getStateHelper().put(PropertyKeys.tzoneOffset, offset);
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
            date, size, showHour, showMinute, showSecond, am, tzoneOffset, widgetVar;
	}
}
