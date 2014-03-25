package it.strazz.faces.analogclock;

import java.util.Date;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.api.Widget;
import org.primefaces.component.clock.Clock;

@FacesComponent(value = AnalogClock.COMPONENT_TYPE)
@ResourceDependencies({
		@ResourceDependency(library = "moment", name = "moment.min.js"),
		@ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
		@ResourceDependency(library = "raphael", name = "raphael-min.js"),
		@ResourceDependency(library = "primefaces", name = "primefaces.js"),
		@ResourceDependency(library = "strazzfaces", name = "analog-clock.js") })
public class AnalogClock extends UIComponentBase implements Widget {

	public static final String COMPONENT_TYPE = "it.strazz.faces.AnalogClock";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public void setStartTime(Date _pattern) {
		getStateHelper().put(PropertyKeys.startTime, _pattern);
	}

	public Date getStartTime() {
		return (Date) getStateHelper().eval(PropertyKeys.startTime, new Date());
	}

	public String getMode() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.mode,"client");
	}

	public void setMode(String _mode) {
		getStateHelper().put(PropertyKeys.mode, _mode);
	}

	public String getWidgetVar() {
		return (String) getStateHelper().eval(PropertyKeys.widgetVar, null);
	}

	public void setWidgetVar(String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
	}
	
	public Integer getWidth(){
		return (Integer) this.getStateHelper().eval(PropertyKeys.width,null);
	}
	
	public void setWidth(Integer width){
		this.getStateHelper().put(PropertyKeys.width, width);
	}

	public String resolveWidgetVar() {
		FacesContext context = getFacesContext();
		String userWidgetVar = (String) getAttributes().get("widgetVar");

		if (userWidgetVar != null)
			return userWidgetVar;
		else
			return "widget_"
					+ getClientId(context).replaceAll(
							"-|" + UINamingContainer.getSeparatorChar(context),
							"_");
	}

	protected static enum PropertyKeys {
		width, widgetVar, startTime, mode;
	}
}
