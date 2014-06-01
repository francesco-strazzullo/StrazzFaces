package it.strazz.faces.gchart;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIOutput;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.FacesEvent;

import org.primefaces.component.api.Widget;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.util.Constants;

@FacesComponent(value = GChart.COMPONENT_TYPE)
@ResourceDependencies({
		@ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
		@ResourceDependency(library = "primefaces", name = "primefaces.js"),
		@ResourceDependency(library = "strazzfaces", name = "gchart.js"),
		@ResourceDependency(library = "strazzfaces/css", name = "gchart.css") })
public class GChart extends UIOutput implements Widget,ClientBehaviorHolder {
	public static final String COMPONENT_TYPE = "it.strazz.faces.GChart";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";
	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("select"));
	static final String DEFAULT_TYPE = "select";
	
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	@Override
	public String getDefaultEventName() {
		return DEFAULT_TYPE;
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
	
	@Override
	public void queueEvent(FacesEvent event) {
		
		FacesContext context = getFacesContext();
		if(isRequestSource(context) && event instanceof AjaxBehaviorEvent) {
           Map<String,String> params = context.getExternalContext().getRequestParameterMap();
            String eventName = params.get(Constants.RequestParams.PARTIAL_BEHAVIOR_EVENT_PARAM);

            if(eventName.equals("select")) {
                AjaxBehaviorEvent behaviorEvent = (AjaxBehaviorEvent) event;
                String clientId = this.getClientId(context);
                
                Object value = params.get(clientId + "_hidden");
                try {
					value = new JSONArray(value.toString());
				} catch (JSONException e) {
					e.printStackTrace();
					value = "";
				}
                
                SelectEvent selectEvent = new SelectEvent(this, behaviorEvent.getBehavior(), value);
                selectEvent.setPhaseId(behaviorEvent.getPhaseId());

                super.queueEvent(selectEvent);
            }
        }
        else {
            super.queueEvent(event);
        }
	}
	
	public boolean isRequestSource(FacesContext context) {
        String partialSource = context.getExternalContext().getRequestParameterMap().get(Constants.RequestParams.PARTIAL_SOURCE_PARAM);

        return partialSource != null && this.getClientId(context).equals(partialSource);
    }
	
	protected static enum PropertyKeys {
		widgetVar,
		width,
		height,
		title;
	}
}
