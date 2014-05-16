package it.strazz.faces.extpanel;

import it.strazz.faces.util.Util;
import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

@FacesRenderer(componentFamily = ExtPanel.COMPONENT_FAMILY, rendererType = ExtPanelRenderer.RENDERER_TYPE)
/**
 * @author f1l0
 */
public class ExtPanelRenderer extends CoreRenderer {

    public static final String RENDERER_TYPE = "it.strazz.faces.ExtPanelRenderer";

    @Override
    public void decode(FacesContext context, UIComponent component) {

        ExtPanel extpanelComponent = (ExtPanel) component;
        decodeBehaviors(context, extpanelComponent);
        String submittedValue = (String) context.getExternalContext()
                                                .getRequestParameterMap()
                                                .get(extpanelComponent.getClientId(context) + "_hidden");

        extpanelComponent.setSubmittedValue(Boolean.parseBoolean(submittedValue));
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        ExtPanel extpanelComponent = (ExtPanel) component;
        ResponseWriter writer = context.getResponseWriter();

        if(extpanelComponent.getPosition()==null || extpanelComponent.getPosition().isEmpty())
            extpanelComponent.setPosition(ExtPanel.DEFAULT_POSITION);
        else extpanelComponent.setPosition(extpanelComponent.getPosition().toLowerCase());

        if("bottom".equalsIgnoreCase(extpanelComponent.getPosition()))
            encodeBeginBottom(extpanelComponent, writer);
        else encodeBeginTop(extpanelComponent, writer); // Same for Left, Right, Top
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ExtPanel extpanelComponent = (ExtPanel) component;
        encodeMarkup(context, extpanelComponent);
        encodeScript(context, extpanelComponent);
    }

    private void encodeScript(FacesContext context, ExtPanel extpanelComponent) throws IOException {
        String clientId = extpanelComponent.getClientId();
        String widgetVar = extpanelComponent.resolveWidgetVar();

        WidgetBuilder wb = getWidgetBuilder(context);

        wb.init("ExtPanel", widgetVar, clientId);
        wb.attr("widgetName", widgetVar);
        wb.attr("position", getExtPanelPosition(extpanelComponent));
        wb.attr("onopen", extpanelComponent.getOnopen());
        wb.attr("onclose", extpanelComponent.getOnclose());
        encodeClientBehaviors(context, extpanelComponent);

        wb.finish();
    }
	
    private String getExtPanelPosition(ExtPanel extpanelComponent){
        String position = String.valueOf(extpanelComponent.getPosition());
        return ExtPanel.AVAIABLE_POSITIONS.contains(position) ? position : ExtPanel.DEFAULT_POSITION;
    }

    private void encodeMarkup(FacesContext context, ExtPanel extpanelComponent) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        if("bottom".equalsIgnoreCase(extpanelComponent.getPosition()))
            encodeMarkupBottom(writer);
        else encodeMarkupTop(extpanelComponent, writer); // Same for Left, Right, Top
    }
    
    private void encodeBeginTop(ExtPanel extpanelComponent, ResponseWriter writer) throws IOException {
        // ExtPanel
        writer.startElement("div", extpanelComponent);
        writer.writeAttribute("id", extpanelComponent.getClientId(), null);
        if(Util.isValid(extpanelComponent.getStyleClass()))
            writer.writeAttribute("class", "extpanel extpanel-close ui-widget extpanel-"+ extpanelComponent.getPosition() 
                                          +" "+ extpanelComponent.getStyleClass(), null);
        else writer.writeAttribute("class", "extpanel extpanel-close ui-widget extpanel-"+ extpanelComponent.getPosition(), null);

        if(Util.isValid(extpanelComponent.getStyle()))
            writer.writeAttribute("style", extpanelComponent.getStyle(), null);

        // ExtPanel Content
        writer.startElement("div", extpanelComponent);
        writer.writeAttribute("class", "extpanel-content ui-panel ui-widget-content ui-corner-all ui-shadow", null);
        writer.writeAttribute("style", "display:none", null);
    }

    private void encodeBeginBottom(ExtPanel extpanelComponent, ResponseWriter writer) throws IOException {
        // ExtPanel
        writer.startElement("div", extpanelComponent);
        writer.writeAttribute("id", extpanelComponent.getClientId(), null);
        if(Util.isValid(extpanelComponent.getStyleClass()))
            writer.writeAttribute("class", "extpanel extpanel-close ui-widget extpanel-"+ extpanelComponent.getPosition() 
                                          +" "+ extpanelComponent.getStyleClass(), null);
        else writer.writeAttribute("class", "extpanel extpanel-close ui-widget extpanel-"+ extpanelComponent.getPosition(), null);

        if(Util.isValid(extpanelComponent.getStyle()))
            writer.writeAttribute("style", extpanelComponent.getStyle(), null);
        
        // ExtPanel Header
        writer.startElement("div", extpanelComponent);
        writer.writeAttribute("class", "extpanel-header ui-panel ui-widget-content ui-corner-all ui-shadow", null);

        // ExtPanel Header Content
        writer.startElement("div", extpanelComponent);
        writer.writeAttribute("class", "extpanel-header-content ui-widget-header ui-corner-all", null);

        // Icon
        writer.startElement("span", extpanelComponent);
        writer.writeAttribute("class", "ui-icon ui-icon-circle-triangle-n", null);
        writer.endElement("span");

        // Title
        writer.startElement("span", extpanelComponent);
        writer.writeAttribute("class", "ui-panel-title", null);
        writer.writeText(extpanelComponent.getTitle(), null);
        writer.endElement("span");

        writer.endElement("div");
        writer.endElement("div");
        
        // ExtPanel Content
        writer.startElement("div", extpanelComponent);
        writer.writeAttribute("class", "extpanel-content ui-panel ui-widget-content ui-corner-all ui-shadow", null);
        writer.writeAttribute("style", "display:none", null);
    }

    private void encodeMarkupTop(ExtPanel extpanelComponent, ResponseWriter writer) throws IOException {

        writer.endElement("div");

        // ExtPanel Header
        writer.startElement("div", extpanelComponent);
        writer.writeAttribute("class", "extpanel-header ui-panel ui-widget-content ui-corner-all ui-shadow", null);

        // ExtPanel Header Content
        writer.startElement("div", extpanelComponent);
        writer.writeAttribute("class", "extpanel-header-content ui-widget-header ui-corner-all", null);

        // Icon
        writer.startElement("span", extpanelComponent);
        writer.writeAttribute("class", "ui-icon ui-icon-circle-triangle-s", null);
        writer.endElement("span");

        // Title
        writer.startElement("span", extpanelComponent);
        writer.writeAttribute("class", "ui-panel-title", null);
        writer.writeText(extpanelComponent.getTitle(), null);
        writer.endElement("span");

        writer.endElement("div");
        writer.endElement("div");
        writer.endElement("div");
    }

    private void encodeMarkupBottom(ResponseWriter writer) throws IOException {
        writer.endElement("div");
        writer.endElement("div");
    }

    @Override
    public boolean getRendersChildren() { 
        return true; 
    }
}
