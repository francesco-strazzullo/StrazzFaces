package it.strazz.faces.gchart;

import it.strazz.faces.gchart.model.GChartModel;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.json.JSONArray;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

@FacesRenderer(componentFamily = GChart.COMPONENT_FAMILY, rendererType = GChartRenderer.RENDERER_TYPE)
public class GChartRenderer extends CoreRenderer {

	public static final String RENDERER_TYPE = "it.strazz.faces.GChartRenderer";

	public void decode(FacesContext context, UIComponent component) {
		super.decode(context, component);
		decodeBehaviors(context, component);
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		GChart analogClock = (GChart) component;

		encodeMarkup(context, analogClock);
		encodeScript(context, analogClock);
	}

	protected void encodeMarkup(FacesContext context, GChart chart)	throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		writer.startElement("input", chart);
		writer.writeAttribute("id", chart.getClientId() + "_hidden",null);
		writer.writeAttribute("name",chart.getClientId() + "_hidden", null);
		writer.writeAttribute("type", "hidden", null);
		writer.endElement("input");

		writer.startElement("div", chart);
		writer.writeAttribute("id", chart.getClientId(), null);
		writer.endElement("div");
		
		this.startScript(writer, chart.getClientId());
		writer.writeAttribute("src", "https://www.google.com/jsapi", null);
		this.endScript(writer);
		
	}

	protected void encodeScript(FacesContext context, GChart chart) throws IOException {

		String clientId = chart.getClientId();
		String widgetVar = chart.resolveWidgetVar();

		WidgetBuilder wb = getWidgetBuilder(context);

		wb.init("GChart", widgetVar, clientId);
		
		GChartModel m = (GChartModel) chart.getValue();
		
		String data = new JSONArray().toString();
		if(m != null){
			data = new JSONArray(m.getData()).toString();
		}
		
		wb.attr("data",this.escapeText(data));
		wb.attr("type",m != null ? m.getChartType() : "");
		wb.attr("title",chart.getTitle());
		wb.attr("width",chart.getWidth());
		wb.attr("height",chart.getHeight());
		
		encodeClientBehaviors(context, chart);
		
		wb.finish();
	}
}