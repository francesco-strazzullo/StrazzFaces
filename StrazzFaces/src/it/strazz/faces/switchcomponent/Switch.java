package it.strazz.faces.switchcomponent;

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

@FacesComponent(value = Switch.COMPONENT_TYPE)
@ResourceDependencies({
		@ResourceDependency(library = "drinks", name = "Drinks.js"),
		@ResourceDependency(library = "drinks", name = "Switch.js"),
		@ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
		@ResourceDependency(library = "primefaces", name = "primefaces.js"),
		@ResourceDependency(library = "strazzfaces", name = "switch.js") })
public class Switch extends UIInput implements Widget, ClientBehaviorHolder {
	public static final String COMPONENT_TYPE = "it.strazz.faces.Switch";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("change"));
	static final Collection<String> AVAIABLE_TYPES = Collections.unmodifiableCollection(Arrays.asList("rocker","arc","side","circle","rect","toggle"));
	static final String DEFAULT_TYPE = "toggle";
	
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
	
	public String getType() {
		return (String) getStateHelper().eval(PropertyKeys.type, DEFAULT_TYPE);
	}

	public void setType(String _widgetVar) {
		getStateHelper().put(PropertyKeys.type, _widgetVar);
	}
	
        @Override
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
		type, widgetVar;
	}
}
