package it.strazz.faces.gchart;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;

import org.primefaces.component.api.Widget;

@FacesComponent(value = GChart.COMPONENT_TYPE)
@ResourceDependencies({
		@ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
		@ResourceDependency(library = "primefaces", name = "primefaces.js"),
		@ResourceDependency(library = "strazzfaces", name = "gchart.js") })
public class GChart extends UIOutput implements Widget {
	public static final String COMPONENT_TYPE = "it.strazz.faces.GChart";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";
	
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public String getWidgetVar() {
		return (String) getStateHelper().eval(PropertyKeys.widgetVar, null);
	}

	public void setWidgetVar(String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
	}
	
	public Integer getWidth() {
		return (Integer) this.getStateHelper().eval(PropertyKeys.width, null);
	}

	public void setWidth(Integer width) {
		this.getStateHelper().put(PropertyKeys.width, width);
	}

	public Integer getHeight() {
		return (Integer) this.getStateHelper().eval(PropertyKeys.height, null);
	}

	public void setHeight(Integer width) {
		this.getStateHelper().put(PropertyKeys.height, width);
	}
	
	public String getTitle() {
		return (String) getStateHelper().eval(PropertyKeys.title, null);
	}

	public void setTitle(String title) {
		getStateHelper().put(PropertyKeys.title, title);
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
		widgetVar,
		width,
		height,
		title;
	}
}
