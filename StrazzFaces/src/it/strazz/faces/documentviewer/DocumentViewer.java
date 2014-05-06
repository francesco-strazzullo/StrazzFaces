package it.strazz.faces.documentviewer;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

@FacesComponent(value=DocumentViewer.COMPONENT_TYPE)
public class DocumentViewer extends UIComponentBase {

	public static final String COMPONENT_TYPE = "it.strazz.faces.DocumentViewer";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";
	
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	public Integer getWidth(){
		return (Integer) this.getStateHelper().eval(PropertyKeys.width,null);
	}
	
	public void setWidth(Integer width){
		this.getStateHelper().put(PropertyKeys.width, width);
	}
	
	public Integer getHeight(){
		return (Integer) this.getStateHelper().eval(PropertyKeys.height,null);
	}
	
	public void setHeight(Integer width){
		this.getStateHelper().put(PropertyKeys.height, width);
	}
	
	public String getStyle(){
		return (String) this.getStateHelper().eval(PropertyKeys.style, null);
	}
	
	public void setStyle(String style){
		this.getStateHelper().put(PropertyKeys.style, style);
	}
	
	public String getUrl(){
		return (String) this.getStateHelper().eval(PropertyKeys.url, null);
	}
	
	public void setUrl(String style){
		this.getStateHelper().put(PropertyKeys.url, style);
	}
	
	protected static enum PropertyKeys {
		width, 
		height, 
		style, 
		url;
	}

}
