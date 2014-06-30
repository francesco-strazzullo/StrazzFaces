package it.strazz.faces.gravatar;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

@FacesComponent(value=Gravatar.COMPONENT_TYPE)
public class Gravatar extends UIOutput{
	public static final String COMPONENT_TYPE = "it.strazz.faces.Gravatar";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	public Integer getWidth() {
		return (Integer) this.getStateHelper().eval(PropertyKeys.width, null);
	}

	public void setWidth(Integer width) {
		this.getStateHelper().put(PropertyKeys.width, width);
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
	
	protected static enum PropertyKeys {
		width, style, qrCode;
	}

}
