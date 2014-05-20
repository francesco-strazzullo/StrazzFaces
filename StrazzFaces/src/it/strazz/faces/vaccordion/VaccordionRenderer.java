package it.strazz.faces.vaccordion;

import it.strazz.faces.util.Strings;
import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

@FacesRenderer(componentFamily = Vtab.COMPONENT_FAMILY, rendererType = VaccordionRenderer.RENDERER_TYPE)
/**
 * @author f1l0
 */
public class VaccordionRenderer extends CoreRenderer {

    public static final String RENDERER_TYPE = "it.strazz.faces.VaccordionRenderer";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        Vaccordion vaccordionComponent = (Vaccordion) component;
        ResponseWriter writer = context.getResponseWriter();
        
        encodeScript(context, vaccordionComponent);

        writer.startElement("div", vaccordionComponent);
        writer.writeAttribute("id", vaccordionComponent.getClientId(), null);
        if(Strings.isNotEmpty(vaccordionComponent.getStyleClass()))
            writer.writeAttribute("class", "vaccordion ui-panel ui-widget ui-widget-content ui-corner-all "+ vaccordionComponent.getStyleClass(), null);
        else writer.writeAttribute("class", "vaccordion ui-panel ui-widget ui-widget-content ui-corner-all", null);
        
        if(Strings.isNotEmpty(vaccordionComponent.getStyle()))
            writer.writeAttribute("style", vaccordionComponent.getStyle(), null);
        
        if(vaccordionComponent.isShowHeader()) {
            // Header
            writer.startElement("div", vaccordionComponent);
            writer.writeAttribute("class", "vaccordion-header ui-panel-titlebar ui-widget-header ui-helper-clearfix ui-corner-all", null);

            // Header Title
            writer.startElement("span", vaccordionComponent);
            writer.writeAttribute("class", "vaccordion-header-title ui-panel-title", null);
            writer.writeText(Strings.isNotEmpty(vaccordionComponent.getHeader()) ? vaccordionComponent.getHeader() : "", null);
            writer.endElement("span");

            writer.endElement("div");
        }
        
        // Content
        writer.startElement("div", vaccordionComponent);
        writer.writeAttribute("class", "vaccordion-content ui-panel-content ui-widget-content", null);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        Vaccordion vaccordionComponent = (Vaccordion) component;
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
        
        // Footer
        if(Strings.isNotEmpty(vaccordionComponent.getFooter())) {
            writer.startElement("div", vaccordionComponent);
            writer.writeAttribute("class", "vaccordion-footer ui-panel-footer ui-widget-content", null);
            writer.writeText(vaccordionComponent.getFooter(), null);
            writer.endElement("div");
        }
        
        writer.endElement("div");
    }

    private void encodeScript(FacesContext context, Vaccordion vaccordionComponent) throws IOException {
        String clientId = vaccordionComponent.getClientId();
        String widgetVar = vaccordionComponent.resolveWidgetVar();

        WidgetBuilder wb = getWidgetBuilder(context);

        wb.init("vAccordion",      widgetVar, clientId);
        wb.attr("widgetName",      widgetVar);
        wb.attr("iconPos",         vaccordionComponent.getIconPos());
        wb.attr("iconOpen",        vaccordionComponent.getIconOpen());
        wb.attr("iconClose",       vaccordionComponent.getIconClose());
        wb.attr("styleClassOpen",  vaccordionComponent.getStyleClassOpen());
        wb.attr("styleClassClose", vaccordionComponent.getStyleClassClose());

        wb.finish();
    }
    
}
