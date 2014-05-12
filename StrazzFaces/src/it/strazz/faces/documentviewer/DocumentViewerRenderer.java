package it.strazz.faces.documentviewer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.DynamicResourceBuilder;

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
		
		String imageSrc;
	    try {
	        imageSrc = getDocumentSource(context,documentViewer);
	    } catch (Exception ex) {
	        throw new IOException(ex);
	    }
		
		writer.startElement("iframe", documentViewer);
		writer.writeAttribute("id", documentViewer.getClientId(), null);
		writer.writeAttribute("style", documentViewer.getStyle(), null);
		writer.writeAttribute("width", documentViewer.getWidth() != null ? documentViewer.getWidth() : "100%", null);
		writer.writeAttribute("height", documentViewer.getHeight(), null);
		writer.writeAttribute("allowfullscreen", "", null);
		writer.writeAttribute("webkitallowfullscreen", "", null);
		writer.writeAttribute("src", context.getExternalContext().getRequestContextPath() + "/javax.faces.resource/pdf.js/viewer.html?file=" + URLEncoder.encode(imageSrc, "UTF-8") , null);
		writer.endElement("iframe");
		
	}
	
	protected String getDocumentSource(FacesContext context, DocumentViewer documentViewer) throws UnsupportedEncodingException {
		
		String name = documentViewer.getName();
        
        if (name != null) {
            String libName = documentViewer.getLibrary();
            ResourceHandler handler = context.getApplication().getResourceHandler();
            Resource res = handler.createResource(name, libName);
            
            if (res == null) {
                return "RES_NOT_FOUND";
            } 
            else {
            	String requestPath = res.getRequestPath();
            	return context.getExternalContext().encodeResourceURL(requestPath);
            }
        }
        else {
            return DynamicResourceBuilder.build(context, documentViewer.getValue(), documentViewer, documentViewer.isCache());
        }
	}
}
