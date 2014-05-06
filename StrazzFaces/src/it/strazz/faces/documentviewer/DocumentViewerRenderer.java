package it.strazz.faces.documentviewer;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;

@FacesRenderer(componentFamily=DocumentViewer.COMPONENT_FAMILY,rendererType=DocumentViewerRenderer.RENDERER_TYPE)
public class DocumentViewerRenderer extends CoreRenderer {
	public static final String RENDERER_TYPE = "it.strazz.faces.DocumentViewerRenderer";
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		DocumentViewer documentViewer = (DocumentViewer) component;
		encodeMarkup(context, documentViewer);
	}

	private void encodeMarkup(FacesContext context,DocumentViewer documentViewer) throws IOException {
		
		ResponseWriter writer = context.getResponseWriter();
		
		writer.startElement("iframe", documentViewer);
		writer.writeAttribute("id", documentViewer.getClientId(), null);
		writer.writeAttribute("style", documentViewer.getStyle(), null);
		writer.writeAttribute("width", documentViewer.getWidth() != null ? documentViewer.getWidth() : "100%", null);
		writer.writeAttribute("height", documentViewer.getHeight(), null);
		writer.writeAttribute("allowfullscreen", "", null);
		writer.writeAttribute("webkitallowfullscreen", "", null);
		writer.writeAttribute("src", context.getExternalContext().getRequestContextPath() + "/javax.faces.resource/Viewer.js/index.html#" + documentViewer.getUrl(), null);
		writer.endElement("iframe");
		
	}
}
