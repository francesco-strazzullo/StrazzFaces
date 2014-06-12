package it.strazz.faces.analogclock;

import it.strazz.faces.analogclock.model.ColorTheme;

import java.awt.Color;
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
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		AnalogClock analogClock = (AnalogClock) component;

		encodeMarkup(context, analogClock);
		encodeScript(context, analogClock);
	}

	protected void encodeMarkup(FacesContext context, AnalogClock clock)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		writer.startElement("div", clock);
		writer.writeAttribute("id", clock.getClientId(), null);
		writer.endElement("div");
	}

	protected void encodeScript(FacesContext context, AnalogClock analogClock)
			throws IOException {

		String clientId = analogClock.getClientId();
		String widgetVar = analogClock.resolveWidgetVar();

		WidgetBuilder wb = getWidgetBuilder(context);

		wb.init("AnalogClock", widgetVar, clientId);
		wb.attr("mode", analogClock.getMode());
		wb.attr("time", analogClock.getStartTime() != null ? analogClock.getStartTime().getTime() : null);
		if(analogClock.getColorTheme() != null){
			if(analogClock.getColorTheme() instanceof String){
				wb.attr("colorTheme", analogClock.getColorTheme().toString());
			}else{
				wb.attr("themeObject", this.escapeText(new JSONObject(colorThemeToMap((ColorTheme) analogClock.getColorTheme())).toString()));
			}
		}

		if (analogClock.getWidth() != null) {
			wb.attr("width", analogClock.getWidth());
		}

		wb.finish();
	}
	
	private Map colorThemeToMap(ColorTheme colorTheme){
		
		Map<String,String> map = new HashMap<String,String>(0);
		
		map.put("face", colorToHex(colorTheme.getFace()));
		map.put("border", colorToHex(colorTheme.getBorder()));
		map.put("hourHand", colorToHex(colorTheme.getHourHand()));
		map.put("minuteHand", colorToHex(colorTheme.getMinuteHand()));
		map.put("secondHand", colorToHex(colorTheme.getSecondHand()));
		map.put("secondSigns", colorToHex(colorTheme.getHourSigns()));
		map.put("pin", colorToHex(colorTheme.getPin()));
		
		return map;
	}
	
	private String colorToHex(Color color){
		
		String red = Integer.toHexString(color.getRed());
		if(red.length() < 2){
			red = "0" + red;
		}
		
		String blue = Integer.toHexString(color.getBlue());
		if(blue.length() < 2){
			blue = "0" + blue;
		}
		
		String green = Integer.toHexString(color.getGreen());
		if(green.length() < 2){
			green = "0" + green;
		}
		
		return "#" + red + green + blue;
	}
}