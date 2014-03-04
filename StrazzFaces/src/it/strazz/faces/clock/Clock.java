package it.strazz.faces.clock;

import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import com.sun.faces.application.ApplicationImpl;

@FacesComponent(value=Clock.COMPONENT_TYPE)
@ResourceDependency(library="strazzfaces/coolclock",name="coolclock.js")
public class Clock extends UIComponentBase {

	public static final String COMPONENT_TYPE = "it.strazz.faces.Clock";
	public static final String COMPONENT_FAMILY = "it.strazz.faces.components";
	
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	public boolean isDigital(){
		return Boolean.TRUE.equals(this.getStateHelper().eval(PropertyKeys.digital, true));
	}
	
	public void setDigital(boolean digital){
		this.getStateHelper().put(PropertyKeys.digital, digital);
	}
	
	protected static enum PropertyKeys{
		digital;
	}

}
