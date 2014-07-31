package it.strazz.faces.analogclock;

import it.strazz.faces.analogclock.model.ColorTheme;
import it.strazz.faces.util.Colors;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.json.JSONObject;
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
		wb.attr("time", analogClock.getStartTime() != null ? analogClock.getStartTime().getTime() : null);
		if (analogClock.getColorTheme() != null) {
			if (analogClock.getColorTheme() instanceof String) {
				wb.attr("colorTheme", analogClock.getColorTheme().toString());
			} else {
				wb.attr("themeObject", this.escapeText(new JSONObject(colorThemeToMap((ColorTheme) analogClock.getColorTheme())).toString()));
			}
		}

		if (analogClock.getWidth() != null) {
			wb.attr("width", analogClock.getWidth());
		}

		wb.finish();
	}

	private Map colorThemeToMap(ColorTheme colorTheme) {

		Map<String, String> map = new HashMap<String, String>(0);

		map.put("face", Colors.colorToHex(colorTheme.getFace()));
		map.put("border", Colors.colorToHex(colorTheme.getBorder()));
		map.put("hourHand", Colors.colorToHex(colorTheme.getHourHand()));
		map.put("minuteHand", Colors.colorToHex(colorTheme.getMinuteHand()));
		map.put("secondHand", Colors.colorToHex(colorTheme.getSecondHand()));
		map.put("secondSigns", Colors.colorToHex(colorTheme.getHourSigns()));
		map.put("pin", Colors.colorToHex(colorTheme.getPin()));

		return map;
	}

}