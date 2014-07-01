package it.strazz.faces.gravatar;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

@FacesComponent(value=Gravatar.COMPONENT_TYPE)
public class Gravatar extends UIOutput{
	public static final String COMPONENT_TYPE = "it.strazz.faces.Gravatar";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";
	
	public static final List<String> NOT_FOUND_VALUES = new ArrayList<String>();
	
	static{
		NOT_FOUND_VALUES.add("default");
		NOT_FOUND_VALUES.add("mm");
		NOT_FOUND_VALUES.add("identicon");
		NOT_FOUND_VALUES.add("monsterid");
		NOT_FOUND_VALUES.add("wavatar");
		NOT_FOUND_VALUES.add("retro");
		NOT_FOUND_VALUES.add("blank");
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	public Integer getSize() {
		return (Integer) this.getStateHelper().eval(PropertyKeys.size, null);
	}

	public void setSize(Integer size) {
		this.getStateHelper().put(PropertyKeys.size, size);
	}
	
	public String getStyle() {
		return (String) this.getStateHelper().eval(PropertyKeys.style, null);
	}

	public void setStyle(String style) {
		this.getStateHelper().put(PropertyKeys.style, style);
	}
	
	public boolean isQrCode(){
		return Boolean.TRUE.equals(this.getStateHelper().eval(PropertyKeys.qrCode,false));
	}
	
	public void setQrCode(boolean qrCode) {
		this.getStateHelper().put(PropertyKeys.qrCode, qrCode);
	}
	
	public String getNotFound(){
		return (String) this.getStateHelper().eval(PropertyKeys.notFound, null);
	}
	
	public void setNotFound(String notFound){
		this.getStateHelper().put(PropertyKeys.notFound, notFound);
	}
	
	protected static enum PropertyKeys {
		notFound,size, style, qrCode;
	}

}
