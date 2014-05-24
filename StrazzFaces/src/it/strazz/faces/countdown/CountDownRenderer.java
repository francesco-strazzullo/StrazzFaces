package it.strazz.faces.countdown;

import it.strazz.faces.util.Strings;

import java.io.IOException;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

@FacesRenderer(componentFamily = CountDown.COMPONENT_FAMILY, rendererType = CountDownRenderer.RENDERER_TYPE)
/**
 * @author f1l0
 */
public class CountDownRenderer extends CoreRenderer {

	public static final String RENDERER_TYPE = "it.strazz.faces.CountDownRenderer";

	@Override
	public void decode(FacesContext context, UIComponent component) {

		CountDown countdownComponent = (CountDown) component;
		decodeBehaviors(context, countdownComponent);
		String submittedValue = (String) context.getExternalContext()
				.getRequestParameterMap()
				.get(countdownComponent.getClientId(context) + "_hidden");

		countdownComponent.setSubmittedValue(Boolean
				.parseBoolean(submittedValue));
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		CountDown countdownComponent = (CountDown) component;
		encodeMarkup(context, countdownComponent);
		encodeScript(context, countdownComponent);
	}

	private void encodeScript(FacesContext context, CountDown countdownComponent)
			throws IOException {
		String clientId = countdownComponent.getClientId();
		String widgetVar = countdownComponent.resolveWidgetVar();

		WidgetBuilder wb = getWidgetBuilder(context);

		wb.init("CountDown", widgetVar, clientId);
		wb.attr("widgetName", widgetVar);
		wb.attr("dayText", countdownComponent.getDayText());
		wb.attr("daysText", countdownComponent.getDaysText());
		wb.attr("hoursText", countdownComponent.getHoursText());
		wb.attr("minutesText", countdownComponent.getMinutesText());
		wb.attr("secondsText", countdownComponent.getSecondsText());
		wb.attr("oneDayClass", countdownComponent.isOneDayClass());
		wb.attr("textAfterCount", countdownComponent.getTextAfterCount());
		wb.attr("displayDays", countdownComponent.isDisplayDays());
		wb.attr("displayZeroDays", countdownComponent.isDisplayZeroDays());
		wb.attr("addClass", countdownComponent.getAddClass());
		wb.attr("callback", countdownComponent.getOncomplete());
		encodeClientBehaviors(context, countdownComponent);

		wb.finish();
	}

	private void encodeMarkup(FacesContext context, CountDown countdownComponent)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		writer.startElement("span", countdownComponent);
		writer.writeAttribute("id", countdownComponent.getClientId(), null);
		if (Strings.isNotEmpty(countdownComponent.getStyleClass()))
			writer.writeAttribute("class",
					"countdown ui-widget ui-widget-header ui-corner-all "
							+ countdownComponent.getStyleClass(), null);
		else
			writer.writeAttribute("class",
					"countdown ui-widget ui-widget-header ui-corner-all", null);

		if (Strings.isNotEmpty(countdownComponent.getStyle()))
			writer.writeAttribute("style", countdownComponent.getStyle(), null);
		long seconds = (countdownComponent.getDate().getTime() - new Date()
				.getTime()) / 1000;
		writer.writeAttribute("data-seconds", seconds > 0 ? seconds : 0, null);

		writer.endElement("span");
	}
}
