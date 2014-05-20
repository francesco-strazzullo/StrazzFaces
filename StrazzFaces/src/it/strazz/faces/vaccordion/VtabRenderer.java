package it.strazz.faces.vaccordion;

import it.strazz.faces.util.Strings;
import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;

@FacesRenderer(componentFamily = Vtab.COMPONENT_FAMILY, rendererType = VtabRenderer.RENDERER_TYPE)
/**
 * @author f1l0
 */
public class VtabRenderer extends CoreRenderer {

    public static final String RENDERER_TYPE = "it.strazz.faces.VtabRenderer";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        Vtab vtabComponent = (Vtab) component;
        ResponseWriter writer = context.getResponseWriter();

        // Tab
        writer.startElement("span", vtabComponent);
        if(Strings.isNotEmpty(vtabComponent.getStyleClass()))
            writer.writeAttribute("class", "vtab ui-widget ui-widget-header "+ vtabComponent.getStyleClass(), null);
        else writer.writeAttribute("class", "vtab ui-widget ui-widget-header l", null);
        
        if(Strings.isNotEmpty(vtabComponent.getStyle()))
            writer.writeAttribute("style", vtabComponent.getStyle(), null);

        // Icon
        writer.startElement("a", vtabComponent);
        writer.writeAttribute("class", "vtab-icon ui-panel-titlebar-icon ui-corner-all ui-state-default", null);
        writer.writeAttribute("href", "javascript:void(0)", null);
        writer.startElement("span", vtabComponent);
        writer.writeAttribute("class", "ui-icon", null);
        writer.endElement("span");
        writer.endElement("a");
        
        writer.endElement("span");
        
        // Content
        writer.startElement("div", vtabComponent);
        writer.writeAttribute("style", "display:none", null);
        writer.writeAttribute("class", "vtab-content ui-widget ui-widget-content", null);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
    }
}
