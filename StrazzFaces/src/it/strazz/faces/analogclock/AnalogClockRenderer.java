package it.strazz.faces.analogclock;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

@FacesRenderer(componentFamily = AnalogClock.COMPONENT_FAMILY, rendererType = AnalogClockRenderer.RENDERER_TYPE)
public class AnalogClockRenderer extends CoreRenderer {

	public static final String RENDERER_TYPE = "it.strazz.faces.AnalogClockRenderer";

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		AnalogClock analogClock = (AnalogClock) component;

		encodeMarkup(context, analogClock);
		encodeScript(context, analogClock);
	}

	protected void encodeMarkup(FacesContext context, AnalogClock clock) throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		writer.startElement("div", clock);
		writer.writeAttribute("id", clock.getClientId(), null);
		writer.endElement("div");
	}

	protected void encodeScript(FacesContext context, AnalogClock analogClock) throws IOException {

		String clientId = analogClock.getClientId();
		String widgetVar = analogClock.resolveWidgetVar();

		WidgetBuilder wb = getWidgetBuilder(context);

		wb.init("AnalogClock", widgetVar, clientId);
		wb.attr("mode", analogClock.getMode());
		wb.attr("time",	analogClock.getStartTime() != null ? analogClock.getStartTime().getTime() : null);
		if(analogClock.getWidth() != null){
			wb.attr("width", analogClock.getWidth());
		}
				
		wb.finish();
	}
}