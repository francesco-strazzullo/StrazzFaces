package it.strazz.faces.dockablepanel;

import it.strazz.faces.util.Util;
import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

@FacesRenderer(componentFamily = DockablePanel.COMPONENT_FAMILY, rendererType = DockablePanelRenderer.RENDERER_TYPE)
/**
 * @author f1l0
 */
public class DockablePanelRenderer extends CoreRenderer {

    public static final String RENDERER_TYPE = "it.strazz.faces.DockablePanelRenderer";

    @Override
    public void decode(FacesContext context, UIComponent component) {

        DockablePanel dockablepanelComponent = (DockablePanel) component;
        decodeBehaviors(context, dockablepanelComponent);
        String submittedValue = (String) context.getExternalContext()
                                                .getRequestParameterMap()
                                                .get(dockablepanelComponent.getClientId(context) + "_hidden");

        dockablepanelComponent.setSubmittedValue(Boolean.parseBoolean(submittedValue));
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        DockablePanel dockablepanelComponent = (DockablePanel) component;
        ResponseWriter writer = context.getResponseWriter();

        if(dockablepanelComponent.getPosition()==null || dockablepanelComponent.getPosition().isEmpty())
            dockablepanelComponent.setPosition(DockablePanel.DEFAULT_POSITION);
        else dockablepanelComponent.setPosition(dockablepanelComponent.getPosition().toLowerCase());

        if("bottom".equalsIgnoreCase(dockablepanelComponent.getPosition()))
            encodeBeginBottom(dockablepanelComponent, writer);
        else encodeBeginTop(dockablepanelComponent, writer); // Same for Left, Right, Top
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        DockablePanel dockablepanelComponent = (DockablePanel) component;
        encodeMarkup(context, dockablepanelComponent);
        encodeScript(context, dockablepanelComponent);
    }

    private void encodeScript(FacesContext context, DockablePanel dockablepanelComponent) throws IOException {
        String clientId = dockablepanelComponent.getClientId();
        String widgetVar = dockablepanelComponent.resolveWidgetVar();

        WidgetBuilder wb = getWidgetBuilder(context);

        wb.init("DockablePanel", widgetVar, clientId);
        wb.attr("widgetName", widgetVar);
        wb.attr("position", getExtPanelPosition(dockablepanelComponent));
        wb.attr("onopen", dockablepanelComponent.getOnopen());
        wb.attr("onclose", dockablepanelComponent.getOnclose());
        encodeClientBehaviors(context, dockablepanelComponent);

        wb.finish();
    }
	
    private String getExtPanelPosition(DockablePanel dockablepanelComponent){
        String position = String.valueOf(dockablepanelComponent.getPosition());
        return DockablePanel.AVAIABLE_POSITIONS.contains(position) ? position : DockablePanel.DEFAULT_POSITION;
    }

    private void encodeMarkup(FacesContext context, DockablePanel dockablepanelComponent) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        if("bottom".equalsIgnoreCase(dockablepanelComponent.getPosition()))
            encodeMarkupBottom(writer);
        else encodeMarkupTop(dockablepanelComponent, writer); // Same for Left, Right, Top
    }
    
    private void encodeBeginTop(DockablePanel dockablepanelComponent, ResponseWriter writer) throws IOException {
        // ExtPanel
        writer.startElement("div", dockablepanelComponent);
        writer.writeAttribute("id", dockablepanelComponent.getClientId(), null);
        if(Util.isValid(dockablepanelComponent.getStyleClass()))
            writer.writeAttribute("class", "dockablepanel dockablepanel-close ui-widget dockablepanel-"+ dockablepanelComponent.getPosition() 
                                          +" "+ dockablepanelComponent.getStyleClass(), null);
        else writer.writeAttribute("class", "dockablepanel dockablepanel-close ui-widget dockablepanel-"+ dockablepanelComponent.getPosition(), null);

        if(Util.isValid(dockablepanelComponent.getStyle()))
            writer.writeAttribute("style", dockablepanelComponent.getStyle(), null);

        // ExtPanel Content
        writer.startElement("div", dockablepanelComponent);
        writer.writeAttribute("class", "dockablepanel-content ui-panel ui-widget-content ui-corner-all ui-shadow", null);
        writer.writeAttribute("style", "display:none", null);
    }

    private void encodeBeginBottom(DockablePanel dockablepanelComponent, ResponseWriter writer) throws IOException {
        // ExtPanel
        writer.startElement("div", dockablepanelComponent);
        writer.writeAttribute("id", dockablepanelComponent.getClientId(), null);
        if(Util.isValid(dockablepanelComponent.getStyleClass()))
            writer.writeAttribute("class", "dockablepanel dockablepanel-close ui-widget dockablepanel-"+ dockablepanelComponent.getPosition() 
                                          +" "+ dockablepanelComponent.getStyleClass(), null);
        else writer.writeAttribute("class", "dockablepanel dockablepanel-close ui-widget dockablepanel-"+ dockablepanelComponent.getPosition(), null);

        if(Util.isValid(dockablepanelComponent.getStyle()))
            writer.writeAttribute("style", dockablepanelComponent.getStyle(), null);
        
        // ExtPanel Header
        writer.startElement("div", dockablepanelComponent);
        writer.writeAttribute("class", "dockablepanel-header ui-panel ui-widget-content ui-corner-all ui-shadow", null);

        // ExtPanel Header Content
        writer.startElement("div", dockablepanelComponent);
        writer.writeAttribute("class", "dockablepanel-header-content ui-widget-header ui-corner-all", null);

        // Icon
        writer.startElement("span", dockablepanelComponent);
        writer.writeAttribute("class", "ui-icon ui-icon-circle-triangle-n", null);
        writer.endElement("span");

        // Title
        writer.startElement("span", dockablepanelComponent);
        writer.writeAttribute("class", "ui-panel-title", null);
        writer.writeText(dockablepanelComponent.getTitle(), null);
        writer.endElement("span");

        writer.endElement("div");
        writer.endElement("div");
        
        // ExtPanel Content
        writer.startElement("div", dockablepanelComponent);
        writer.writeAttribute("class", "dockablepanel-content ui-panel ui-widget-content ui-corner-all ui-shadow", null);
        writer.writeAttribute("style", "display:none", null);
    }

    private void encodeMarkupTop(DockablePanel dockablepanelComponent, ResponseWriter writer) throws IOException {

        writer.endElement("div");

        // ExtPanel Header
        writer.startElement("div", dockablepanelComponent);
        writer.writeAttribute("class", "dockablepanel-header ui-panel ui-widget-content ui-corner-all ui-shadow", null);

        // ExtPanel Header Content
        writer.startElement("div", dockablepanelComponent);
        writer.writeAttribute("class", "dockablepanel-header-content ui-widget-header ui-corner-all", null);

        // Icon
        writer.startElement("span", dockablepanelComponent);
        writer.writeAttribute("class", "ui-icon ui-icon-circle-triangle-s", null);
        writer.endElement("span");

        // Title
        writer.startElement("span", dockablepanelComponent);
        writer.writeAttribute("class", "ui-panel-title", null);
        writer.writeText(dockablepanelComponent.getTitle(), null);
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
