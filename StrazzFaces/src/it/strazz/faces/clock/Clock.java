package it.strazz.faces.clock;

import java.util.Date;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.primefaces.component.api.Widget;

@FacesComponent(value=Clock.COMPONENT_TYPE)
@ResourceDependencies({
	@ResourceDependency(library="coolclock",name="coolclock.js"),
	@ResourceDependency(library="moment",name="moment.min.js"),
	@ResourceDependency(library="underscore",name="underscore-min.js"),
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),	
	@ResourceDependency(library="raphael", name="raphael-min.js"),
	@ResourceDependency(library="strazzfaces",name="clock.js")
})

public class Clock extends UIComponentBase implements Widget {

	public static final String COMPONENT_TYPE = "it.strazz.faces.Clock";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";
	
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	public boolean isDigital(){
		return Boolean.TRUE.equals(this.getStateHelper().eval(PropertyKeys.digital, true));
	}
	
	public void setDigital(boolean digital){
		this.getStateHelper().put(PropertyKeys.digital, digital);
	}
	
	public String getPattern() {
		return (String) getStateHelper().eval(PropertyKeys.pattern, "HH:mm:ss");
	}
	
	public void setStartTime(Date _pattern) {
		getStateHelper().put(PropertyKeys.startTime, _pattern);
	}
	
	public Date getStartTime() {
		return (Date) getStateHelper().eval(PropertyKeys.startTime);
	}
	
	public void setPattern(String _pattern) {
		getStateHelper().put(PropertyKeys.pattern, _pattern);
	}
	
	protected static enum PropertyKeys{
		startTime,
		digital,
		pattern;
	}

	public String resolveWidgetVar() {
		FacesContext context = getFacesContext();
		String userWidgetVar = (String) getAttributes().get("widgetVar");

		if(userWidgetVar != null)
			return userWidgetVar;
		 else
			return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}

}
