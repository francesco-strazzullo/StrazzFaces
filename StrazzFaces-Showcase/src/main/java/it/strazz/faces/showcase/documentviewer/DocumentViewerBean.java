package it.strazz.faces.showcase.documentviewer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class DocumentViewerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private StreamedContent content;

	public void onPrerender(ComponentSystemEvent event) {

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			OdfTextDocument odt = OdfTextDocument.newTextDocument();
			for (int i = 0; i < 50; i++) {
				odt.newParagraph("All work and no play makes Jack a dull boy");
			}
			odt.save(out);
			content = new DefaultStreamedContent(new ByteArrayInputStream(
					out.toByteArray()),
					"application/vnd.oasis.opendocument.text");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StreamedContent getContent() {
		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}
}
