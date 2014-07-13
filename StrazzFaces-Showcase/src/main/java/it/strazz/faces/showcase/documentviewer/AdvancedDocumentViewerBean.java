package it.strazz.faces.showcase.documentviewer;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AdvancedDocumentViewerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int page = 2;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
