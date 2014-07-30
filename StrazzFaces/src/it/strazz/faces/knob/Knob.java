package it.strazz.faces.knob;

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

@FacesComponent(value = Knob.COMPONENT_TYPE)
@ResourceDependencies({ @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"), @ResourceDependency(library = "primefaces", name = "primefaces.js"), @ResourceDependency(library = "knob", name = "jquery.knob.js"), @ResourceDependency(library = "strazzfaces", name = "core.js"), @ResourceDependency(library = "strazzfaces", name = "knob.js") })
public class Knob extends UIInput implements Widget, ClientBehaviorHolder {
	public static final String COMPONENT_TYPE = "it.strazz.faces.Knob";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("change"));

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	@Override
	public String getDefaultEventName() {
		return "change";
	}

	public String getWidgetVar() {
		return (String) getStateHelper().eval(PropertyKeys.widgetVar, null);
	}

	public void setWidgetVar(String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
	}

	@Override
	public String resolveWidgetVar() {
		FacesContext context = getFacesContext();
		String userWidgetVar = (String) getAttributes().get("widgetVar");

		if (userWidgetVar != null) {
			return userWidgetVar;
		} else {
			return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
		}

	}

	public int getMin() {
		return (Integer) getStateHelper().eval(PropertyKeys.min, 0);
	}

	public void setMin(int min) {
		this.getStateHelper().put(PropertyKeys.min, min);
	}

	public int getMax() {
		return (Integer) getStateHelper().eval(PropertyKeys.max, 100);
	}

	public void setMax(int max) {
		this.getStateHelper().put(PropertyKeys.max, max);
	}

	public int getStep() {
		return (Integer) getStateHelper().eval(PropertyKeys.step, 1);
	}

	public void setStep(int step) {
		this.getStateHelper().put(PropertyKeys.step, step);
	}

	public Object getWidth() {
		return this.getStateHelper().eval(PropertyKeys.width);
	}

	public void setWidth(Object width) {
		this.getStateHelper().put(PropertyKeys.width, width);
	}

	public String getOnchange() {
		return (String) this.getStateHelper().eval(PropertyKeys.onchange);
	}

	public void setOnchange(String onchange) {
		this.getStateHelper().put(PropertyKeys.onchange, onchange);
	}

	public boolean isShowLabel() {
		return (Boolean) getStateHelper().eval(PropertyKeys.showLabel, true);
	}

	public void setShowLabel(boolean showLabel) {
		this.getStateHelper().put(PropertyKeys.showLabel, showLabel);
	}

	public String getLabelTemplate() {
		return (String) this.getStateHelper().eval(PropertyKeys.labelTemplate, "{value}");
	}

	public void setLabelTemplate(String labelTemplate) {
		this.getStateHelper().put(PropertyKeys.labelTemplate, labelTemplate);
	}

	protected static enum PropertyKeys {
		showLabel,
		labelTemplate,
		onchange,
		width,
		step,
		min,
		max,
		widgetVar;
	}
}
