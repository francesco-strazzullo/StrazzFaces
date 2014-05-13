package it.strazz.faces.countdown;

import java.io.IOException;

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

        countdownComponent.setSubmittedValue(Boolean.parseBoolean(submittedValue));
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        CountDown countdownComponent = (CountDown) component;
        ResponseWriter writer = context.getResponseWriter();
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        CountDown countdownComponent = (CountDown) component;
        encodeMarkup(context, countdownComponent);
        encodeScript(context, countdownComponent);
    }

    private void encodeScript(FacesContext context, CountDown countdownComponent) throws IOException {
        String clientId = countdownComponent.getClientId();
        String widgetVar = countdownComponent.resolveWidgetVar();

        WidgetBuilder wb = getWidgetBuilder(context);

        wb.init("CountDown", widgetVar, clientId);
        wb.attr("widgetName", widgetVar);
        wb.attr("size", getCountDownSize(countdownComponent));
        encodeClientBehaviors(context, countdownComponent);

        wb.finish();
    }
	
    private String getCountDownSize(CountDown countdownComponent){
        String size = String.valueOf(countdownComponent.getSize());
        return CountDown.AVAIABLE_SIZE.contains(size) ? size : CountDown.DEFAULT_SIZE;
    }

    private void encodeMarkup(FacesContext context, CountDown countdownComponent) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

//        if(extpanelComponent.getPosition()==null || extpanelComponent.getPosition().isEmpty())
//            extpanelComponent.setPosition(CountDown.DEFAULT_POSITION);
//
//        if("top".equalsIgnoreCase(extpanelComponent.getPosition()))
//                encodeMarkupTop(extpanelComponent, writer);
//        else if("bottom".equalsIgnoreCase(extpanelComponent.getPosition()))
//                encodeMarkupBottom(writer);
    }
    
    private void encodeBeginTop(FacesContext context, CountDown countdownComponent, ResponseWriter writer) throws IOException {
//        writer.startElement("div", countdownComponent);
//        writer.writeAttribute("id", countdownComponent.getClientId(), null);
//        writer.writeAttribute("class", "extpanel extpanel-close ui-widget extpanel-"+ countdownComponent.getPosition(), null);
//
//        writer.startElement("div", countdownComponent);
//        writer.writeAttribute("class", "extpanel-content ui-panel ui-widget-content ui-corner-all ui-shadow", null);
//        writer.writeAttribute("style", "display:none", null);
    }

    private void encodeBeginBottom(FacesContext context, CountDown countdownComponent, ResponseWriter writer) throws IOException {
//        // ExtPanel
//        writer.startElement("div", countdownComponent);
//        writer.writeAttribute("id", countdownComponent.getClientId(), null);
//        writer.writeAttribute("class", "extpanel extpanel-close ui-widget extpanel-"+ countdownComponent.getPosition(), null);
//        
//        // ExtPanel Header
//        writer.startElement("div", countdownComponent);
//        writer.writeAttribute("class", "extpanel-header ui-panel ui-widget-content ui-corner-all ui-shadow", null);
//
//        // ExtPanel Header Content
//        writer.startElement("div", countdownComponent);
//        writer.writeAttribute("class", "extpanel-header-content ui-widget-header ui-corner-all", null);
//
//        // Icon
//        writer.startElement("span", countdownComponent);
//        writer.writeAttribute("class", "ui-icon ui-icon-circle-triangle-n", null);
//        writer.endElement("span");
    }

    private void encodeMarkupTop(CountDown countdownComponent, ResponseWriter writer) throws IOException {

//        writer.endElement("div");
//
//        // ExtPanel Header
//        writer.startElement("div", extpanelComponent);
//        writer.writeAttribute("class", "extpanel-header ui-panel ui-widget-content ui-corner-all ui-shadow", null);
//
//        // ExtPanel Header Content
//        writer.startElement("div", extpanelComponent);
//        writer.writeAttribute("class", "extpanel-header-content ui-widget-header ui-corner-all", null);
//
//        // Icon
//        writer.startElement("span", extpanelComponent);
//        writer.writeAttribute("class", "ui-icon ui-icon-circle-triangle-s", null);
//        writer.endElement("span");
//
//        // Title
//        writer.startElement("span", extpanelComponent);
//        writer.writeAttribute("class", "ui-panel-title", null);
//        writer.writeText(extpanelComponent.getTitle(), null);
//        writer.endElement("span");
//
//        writer.endElement("div");
//        writer.endElement("div");
//        writer.endElement("div");
    }

    @Override
    public boolean getRendersChildren() { 
        return true; 
    }
}
