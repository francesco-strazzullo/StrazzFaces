package it.strazz.faces.documentviewer;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIGraphic;

@FacesComponent(value=DocumentViewer.COMPONENT_TYPE)
public class DocumentViewer extends UIGraphic {

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
	
	public String getName() {
		return (String) getStateHelper().eval(PropertyKeys.name, null);
	}
	
	public void setName(String _name) {
		getStateHelper().put(PropertyKeys.name, _name);
	}

	public String getLibrary() {
		return (String) getStateHelper().eval(PropertyKeys.library, null);
	}
	
	public void setLibrary(String _library) {
		getStateHelper().put(PropertyKeys.library, _library);
	}
	
	protected static enum PropertyKeys {
		width, 
		height, 
		style, 
		name, 
		library;
	}

}
