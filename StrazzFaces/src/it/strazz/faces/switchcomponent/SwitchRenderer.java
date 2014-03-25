package it.strazz.faces.switchcomponent;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

@FacesRenderer(componentFamily = Switch.COMPONENT_FAMILY, rendererType = SwitchRenderer.RENDERER_TYPE)
public class SwitchRenderer extends CoreRenderer {

	public static final String RENDERER_TYPE = "it.strazz.faces.SwitchRenderer";

	@Override
	public void decode(FacesContext context, UIComponent component) {

		Switch switchComponent = (Switch) component;

		decodeBehaviors(context, switchComponent);

		String submittedValue = (String) context.getExternalContext()
				.getRequestParameterMap()
				.get(switchComponent.getClientId(context) + "_hidden");

		switchComponent.setSubmittedValue(Boolean.parseBoolean(submittedValue));
	}

	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		Switch switchComponent = (Switch) component;

		encodeMarkup(context, switchComponent);
		encodeScript(context, switchComponent);
	}

	private void encodeScript(FacesContext context, Switch switchComponent)
			throws IOException {
		String clientId = switchComponent.getClientId();
		String widgetVar = switchComponent.resolveWidgetVar();

		WidgetBuilder wb = getWidgetBuilder(context);

		wb.init("Switch", widgetVar, clientId);
		wb.attr("widgetName", widgetVar);
		wb.attr("type", getSwitchType(switchComponent));
		encodeClientBehaviors(context, switchComponent);

		wb.finish();

	}
	
	private String getSwitchType(Switch switchComponent){
		String type = String.valueOf(switchComponent.getType());
		return Switch.AVAIABLE_TYPES.contains(type) ? type : Switch.DEFAULT_TYPE;
	}

	private void encodeMarkup(FacesContext context, Switch switchComponent) throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		writer.startElement("div", switchComponent);
		writer.writeAttribute("id", switchComponent.getClientId(), null);

		writer.startElement("input", switchComponent);
		writer.writeAttribute("id", switchComponent.getClientId() + "_hidden",null);
		writer.writeAttribute("name",switchComponent.getClientId() + "_hidden", null);
		writer.writeAttribute("type", "hidden", null);
		writer.writeAttribute("value", Boolean.TRUE.equals(switchComponent.getValue()) + "", null);
		writer.endElement("input");

		writer.endElement("div");
	}

}
